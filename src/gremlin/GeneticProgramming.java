package gremlin;

import org.epochx.gr.op.crossover.WhighamCrossover;
import org.epochx.gr.op.init.RampedHalfAndHalfInitialiser;
import org.epochx.gr.op.mutation.WhighamMutation;
import org.epochx.op.Crossover;
import org.epochx.op.Initialiser;
import org.epochx.op.Mutation;
import org.epochx.op.ProgramSelector;
import org.epochx.op.selection.TournamentSelector;
import org.epochx.representation.CandidateProgram;
import org.epochx.tools.grammar.Grammar;
import org.epochx.tools.random.MersenneTwisterFast;
import org.epochx.tools.random.RandomNumberGenerator;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static gremlin.grammar.Utils.completeGrammar;
import static weka.core.Utils.splitOptions;

public class GeneticProgramming {

    private Instances instances;

    private Grammar grammar;
    private RandomNumberGenerator rng;
    private ProgramSelector programSelector;
    private Initialiser initialiser;
    private Crossover crossover;
    private Mutation mutation;

    private String grammarPath;

    private int populationSize;
    private int noGenerations;
    private int noElites;
    private int initialDepth;
    private int maxDepth;
    private int tournamentSize;
    private int noFolds;

    private double crossoverProbability;
    private double mutationProbability;

    private CandidateProgram bestProgram;
    private double bestFitness;

    private List<Pair> log;

    private class Pair<K, V> {
        private K key;
        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public void init(Instances instances, String grammarPath, int populationSize, int noGenerations,
                     int noElites, int initialDepth, int maxDepth, double crossoverProbability,
                     double mutationProbability, int tournamentSize, int noFolds) throws IOException {
        this.instances = instances;
        this.grammarPath = grammarPath;
        this.populationSize = populationSize;
        this.noGenerations = noGenerations;
        this.noElites = noElites;
        this.initialDepth = initialDepth;
        this.maxDepth = maxDepth;
        this.tournamentSize = tournamentSize;
        this.noFolds = noFolds;

        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;

        this.grammar = new Grammar(new File(grammarPath));
        this.rng = new MersenneTwisterFast(System.nanoTime());
        this.programSelector = new TournamentSelector(this.rng, this.tournamentSize);
        this.initialiser = new RampedHalfAndHalfInitialiser(this.rng, this.grammar, this.populationSize, this.initialDepth, this.maxDepth, true);
        this.crossover = new WhighamCrossover(this.rng);
        this.mutation = new WhighamMutation(this.rng);

        this.log = new ArrayList<>(noGenerations);
    }

    public String run() throws Exception {
        bestProgram = null;
        bestFitness = 0;

        List<CandidateProgram> population = initialiser.getInitialPopulation();
        updateBestProgram(population);

        int generation = 1;
//        while(generation <= noGenerations) {
            List<CandidateProgram> newPopulation = null;

            newPopulation = new ArrayList<>(populationSize);
            newPopulation.addAll(elitsm(population));

            programSelector.setSelectionPool(population);

//            while(newPopulation.size() < populationSize) {
                final double randomCrossover = rng.nextDouble();
                final double randomMutation = rng.nextDouble();



                final CandidateProgram[] children;

//                if(randomCrossover < crossoverProbability) {
                    children = crossover(population);
//                }
//            }

//            generation++;
//        }


        return bestProgram.toString();
    }

    private double getFitness(CandidateProgram candidateProgram) throws Exception {
        String program = candidateProgram.toString();
        program = completeGrammar(program);

        String[] options = splitOptions(program);
        String name = options[0];
        options[0] = "";

        Classifier classifier = AbstractClassifier.forName(name, options);

        if(classifier.getCapabilities().test(instances)) {
            classifier.buildClassifier(instances);

            Evaluation evaluation = new Evaluation(instances);
            evaluation.crossValidateModel(classifier, instances, noFolds, new Random(System.nanoTime()));

            double fitness = evaluation.weightedFMeasure();

            if(Double.isNaN(fitness))
                return 0;
            else
                return fitness;

        } else
            return 0;
    }

    private void updateBestProgram(List<CandidateProgram> population) throws Exception {
        for(final CandidateProgram program: population) {
            final double fitness = getFitness(program);
            if(fitness > bestFitness) {
                bestFitness = fitness;
                bestProgram = program;

                Pair p = new Pair(bestProgram, bestFitness);
                log.add(p);
            }
        }
    }

    private void updateFitness(List<CandidateProgram> population) throws Exception {
        for(int i = 0; i < population.size(); i++) {
            double fitness = getFitness(population.get(i));
            population.get(i).setFitness(fitness);
        }
    }

    private List<CandidateProgram> elitsm(List<CandidateProgram> population) throws Exception {
        List<CandidateProgram> elites = null;

        updateFitness(population);

        Collections.sort(population);
        elites = new ArrayList<>(population.subList(population.size() - noElites, population.size()));

        return elites;
    }

    public CandidateProgram[] crossover(List<CandidateProgram> population) {
        CandidateProgram parent1;
        CandidateProgram parent2;
        CandidateProgram[] children;

        parent1 = programSelector.getProgram();
        parent2 = programSelector.getProgram();
        
        do {
            children = crossover.crossover(parent1.clone(), parent2.clone());
        } while(children == null);

        return children;
    }

    public void mutation(List<CandidateProgram> population) {

    }

}
