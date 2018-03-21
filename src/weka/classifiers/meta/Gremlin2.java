package weka.classifiers.meta;

import org.epochx.gr.op.crossover.WhighamCrossover;
import org.epochx.gr.op.init.RampedHalfAndHalfInitialiser;
import org.epochx.gr.op.mutation.WhighamMutation;
import org.epochx.op.*;
import org.epochx.op.selection.TournamentSelector;
import org.epochx.representation.CandidateProgram;
import org.epochx.tools.grammar.Grammar;
import org.epochx.tools.random.MersenneTwisterFast;
import org.epochx.tools.random.RandomNumberGenerator;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static gremlin.Utils.completeConfiguration;
import static weka.core.Utils.splitOptions;

public class Gremlin2 extends AbstractClassifier {

    private static final long serialVersionUID = 9176095897363227807L;

    private long seed = 1;

    private Classifier classifier;

    private RandomNumberGenerator rng;
    private CandidateProgram bestProgram;
    private Grammar grammar;
    private ProgramSelector programSelector;
    private Initialiser initialiser;
    private Crossover crossoverOperator;
    private Mutation mutationOperator;

    private int noGenerations;
    private int populationSize;
    private int noElites;
    private int maxDepth;
    private int maxInitialDepth;
    private int tournamentSize;
    private int crossValFolds;

    private double terminationFitness;
    private double crossoverProbability;
    private double mutationProbability;
    private double reproductionProbability;
    private double bestFitness;

    private List<CandidateProgram> bestProgramAtEachGeneration;
    private List<Double> bestFitnessAtEachGeneration;

    public static void main(String[] args) {
        AbstractClassifier.runClassifier(new Gremlin2(), args);
    }

    public Gremlin2() {
        String grammarPath = "grammar/basic.bnf";

        this.noGenerations = 50;
        this.populationSize = 100;
        this.noElites = 1;
        this.maxDepth = 6;
        this.maxInitialDepth = 5;
        this.tournamentSize = 3;
        this.crossValFolds = 10;

        this.terminationFitness = 1.0;
        this.crossoverProbability = 0.7;
        this.mutationProbability = 0.3;
        this.reproductionProbability = 1.0;

        this.classifier = null;
        this.bestProgram = null;
        this.bestFitness = 0.0;
        this.bestFitnessAtEachGeneration = new ArrayList<>(noGenerations);
        this.bestProgramAtEachGeneration = new ArrayList<>(noGenerations);

        this.rng = new MersenneTwisterFast();
        this.programSelector = new TournamentSelector(rng, tournamentSize);
        this.initialiser = new RampedHalfAndHalfInitialiser(rng, grammar, populationSize, maxInitialDepth, maxDepth, true);
        this.crossoverOperator = new WhighamCrossover(rng);
        this.mutationOperator = new WhighamMutation(rng);

        try {
            this.grammar = new Grammar(new File(grammarPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {
        bestProgram = null;
        bestFitness = Double.POSITIVE_INFINITY;

        List<CandidateProgram> population = initialiser.getInitialPopulation();

        updateBestProgram(population, instances);

        int gen = 1;
        while ((gen <= noGenerations) || (noGenerations == -1)) {
            population = generation(population);

            updateBestProgram(population, instances);

            if (bestFitness <= terminationFitness) {
                break;
            }

            gen++;
        }

        print();

        classifier = new J48();
        classifier.buildClassifier(instances);
    }

    public List<CandidateProgram> generation(List<CandidateProgram> oldPopulation) {
        List<CandidateProgram> population;

        do {
            population = new ArrayList<>(populationSize);
            population.addAll(elitism(oldPopulation));

            programSelector.setSelectionPool(oldPopulation);

            while(population.size() < populationSize) {
                final double random = rng.nextDouble();

                if(random < crossoverProbability) {
                    final CandidateProgram[] children = crossover();
                    for(final CandidateProgram c: children) {
                        if(population.size() < populationSize)
                            population.add(c);
                    }
                }
                else if(random < crossoverProbability + mutationProbability){
                   population.add(mutate());
                }
                else {
                    population.add(reproduce());
                }
            }

        } while(population == null);

        return population;
    }

    public List<CandidateProgram> elitism(List<CandidateProgram> population) {
        List<CandidateProgram> elites = null;

        if(noElites > 0) {
            Collections.sort(population);
            elites = new ArrayList<>(population.subList(population.size() - noElites, population.size()));
        }
        else {
            elites = new ArrayList<>();
        }

        assert (elites.size() == noElites);

        return elites;
    }

    public CandidateProgram[] crossover() {
        CandidateProgram parent1;
        CandidateProgram parent2;

        CandidateProgram clone1;
        CandidateProgram clone2;

        CandidateProgram[] parents;
        CandidateProgram[] children;

        do {
            parent1 = programSelector.getProgram();
            parent2 = programSelector.getProgram();

            clone1 = parent1.clone();
            clone2 = parent2.clone();
            parents = new CandidateProgram[]{parent1, parent2};

            children = crossoverOperator.crossover(clone1, clone2);

            if(children == null || !allValid(children)) {
                children = null;
                continue;
            }

        } while(children == null);

        return children;
    }

    public CandidateProgram mutate() {
        CandidateProgram parent = null;
        CandidateProgram child = null;

        do {
            parent = programSelector.getProgram();

            child = parent.clone();

            child = mutationOperator.mutate(child);

            if(child == null || !child.isValid()) {
                child = null;
                continue;
            }
        } while(child == null);

        return child;
    }

    public CandidateProgram reproduce() {
        CandidateProgram parent = null;

        do {
            parent = programSelector.getProgram();
        } while(parent == null);

        return parent;
    }

    public boolean allValid(final CandidateProgram[] programs) {
        assert (programs != null);

        boolean valid = true;
        for (final CandidateProgram p: programs) {
            if (!p.isValid()) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    public void updateBestProgram(List<CandidateProgram> population, Instances instances) throws Exception {
        for (final CandidateProgram program: population) {
            final double fitness = getFitness(program, instances);
            if (fitness < bestFitness) {
                bestFitness = fitness;
                bestProgram = program;

                bestProgramAtEachGeneration.add(bestProgram);
                bestFitnessAtEachGeneration.add(bestFitness);
            }
        }

        assert (bestProgram != null);
    }

    public double getFitness(CandidateProgram program, Instances instances) throws Exception {
        String candidate = program.toString();
        candidate = completeConfiguration(candidate);

        String[] options;

        options = splitOptions(candidate);

        String name = options[0];
        options[0] = "";

        Classifier individual = AbstractClassifier.forName(name, options);
        Evaluation evaluation = new Evaluation(instances);

        if(individual.getCapabilities().test(instances)) {
            evaluation.crossValidateModel(individual, instances, 10, new Random());
            return evaluation.weightedFMeasure();
        }
        else return 0.0;
    }

    public void print() {
        for(int i = 0; i < noGenerations; i++) {
            System.out.println(i + "\t" + bestFitnessAtEachGeneration.get(i) + "\t" + bestProgramAtEachGeneration.get(i));
        }
    }

    @Override
    public double classifyInstance(Instance instance) throws Exception {
        return classifier.classifyInstance(instance);
    }

    @Override
    public double[] distributionForInstance(Instance instance) throws Exception {
        return classifier.distributionForInstance(instance);
    }

    @Override
    public String[] getOptions() {
        return super.getOptions();
    }

    @Override
    public void setOptions(String[] options) throws Exception {
        super.setOptions(options);
    }

    @Override
    public Capabilities getCapabilities() {
        return super.getCapabilities();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getNoGenerations() {
        return noGenerations;
    }

    public void setNoGenerations(int noGenerations) {
        if(noGenerations >= -1)
            this.noGenerations = noGenerations;
        else throw new IllegalArgumentException("Number of generations must be greater than or equal to -1!");
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        if(populationSize >= 1)
            this.populationSize = populationSize;
        else throw new IllegalArgumentException("Population size must be greater than or equal to 1!");
    }

    public int getNoElites() {
        return noElites;
    }

    public void setNoElites(int noElites) {
        if(noElites >= 0)
            this.noElites = noElites;
        else throw new IllegalArgumentException("Number of elites must be greater than or equal to 0!");
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        if(maxDepth >= 1 || maxDepth == -1)
            this.maxDepth = maxDepth;
        else throw new IllegalArgumentException("Max depth must be equal to -1 or 1 or greater than 1!");
    }

    public int getMaxInitialDepth() {
        return maxInitialDepth;
    }

    public void setMaxInitialDepth(int maxInitialDepth) {
        if(maxInitialDepth >= 1 || maxInitialDepth == -1)
            this.maxInitialDepth = maxInitialDepth;
        else throw new IllegalArgumentException("Max initial depth must be equal to -1 or 1 or greater than 1!");
    }

    public double getTerminationFitness() {
        return terminationFitness;
    }

    public void setTerminationFitness(double terminationFitness) {
        if(terminationFitness >= 0 && terminationFitness <= 1.0)
            this.terminationFitness = terminationFitness;
        else throw new IllegalArgumentException("Termination fitness must be between 0.0 and 1.0!");
    }

    public double getCrossoverProbability() {
        return crossoverProbability;
    }

    public void setCrossoverProbability(double crossoverProbability) {
        if(crossoverProbability >= 0 && crossoverProbability <= 1.0)
            this.crossoverProbability = crossoverProbability;
        else throw new IllegalArgumentException("Crossover probability must be between 0.0 and 1.0!");

    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public void setMutationProbability(double mutationProbability) {
        if(mutationProbability >= 0 && mutationProbability <= 1.0)
            this.mutationProbability = mutationProbability;
        else throw new IllegalArgumentException("Mutation probability must be between 0.0 and 1.0!");
    }

    public double getReproductionProbability() {
        return reproductionProbability;
    }

    public void setReproductionProbability(double reproductionProbability) {
        if(reproductionProbability >= 0 && reproductionProbability <= 1.0)
            this.reproductionProbability = reproductionProbability;
        else throw new IllegalArgumentException("Reproduction probability must be between 0.0 and 1.0!");
    }
}
