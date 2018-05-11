package gremlin;

import org.epochx.gr.op.crossover.WhighamCrossover;
import org.epochx.gr.op.init.RampedHalfAndHalfInitialiser;
import org.epochx.gr.op.mutation.WhighamMutation;
import org.epochx.gr.representation.GRCandidateProgram;
import org.epochx.op.Crossover;
import org.epochx.op.Initialiser;
import org.epochx.op.Mutation;
import org.epochx.representation.CandidateProgram;
import org.epochx.tools.grammar.Grammar;
import org.epochx.tools.random.MersenneTwisterFast;
import org.epochx.tools.random.RandomNumberGenerator;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static weka.core.Utils.splitOptions;

/**
 * Class responsible for creating and running the evolutionary process.
 */
public class GeneticProgramming {

    private long seed;

    private Instances instances;

    private Grammar grammar;
    private RandomNumberGenerator rng;
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

    private List<Pair<CandidateProgram, Double>> log;

    /**
     * Auxiliary class that builds a pair with two generic objects.
     * @param <K>
     * @param <V>
     */
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

    /**
     * Initialises all necessary values for the execution.
     * @param instances
     * @param seed
     * @param grammarPath
     * @param populationSize
     * @param noGenerations
     * @param noElites
     * @param initialDepth
     * @param maxDepth
     * @param tournamentSize
     * @param noFolds
     * @param crossoverProbability
     * @param mutationProbability
     * @throws IOException
     */
    public GeneticProgramming(Instances instances, long seed, String grammarPath, int populationSize, int noGenerations, int noElites,
                              int initialDepth, int maxDepth, int tournamentSize, int noFolds,
                              double crossoverProbability, double mutationProbability) throws IOException {
        this.seed = seed;
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

        this.instances = instances;

        this.grammar = new Grammar(new File(this.grammarPath));
        this.rng = new MersenneTwisterFast(this.seed);
        this.initialiser = new RampedHalfAndHalfInitialiser(this.rng, this.grammar, this.populationSize, this.initialDepth, this.maxDepth, true);
        this.crossover = new WhighamCrossover(this.rng);
        this.mutation = new WhighamMutation(this.rng);

        this.log = new ArrayList<>(noGenerations);

    }

    /**
     * Runs the evolutionary process.
     * @return
     * @throws Exception
     */
    public String run() throws Exception {
        this.bestProgram = null;
        this.bestFitness = 0;

        List<CandidateProgram> population = initialiser.getInitialPopulation();
        updateBestProgram(population);


        int gen = 1;
        while(gen <= noGenerations) {
            List<CandidateProgram> newPopulation = new ArrayList<>(this.populationSize);

            newPopulation.addAll(elitism(population));

            while(newPopulation.size() < this.populationSize) {
                final double randomCrossover = this.rng.nextDouble();
                final double randomMutation = this.rng.nextDouble();

                if(randomCrossover < this.crossoverProbability) {
                    final CandidateProgram[] children = crossover(population);

                    System.out.println("crossover:");
                    for(int i = 0; i < children.length; i++)
                        System.out.println(children[i].toString());

                    if(randomMutation < this.mutationProbability) {

                        for(int i = 0; i < children.length; i++) {
                            children[i] = mutation(children[i]);
                        }
                    }

                    System.out.println("mutação:");
                    for(int i = 0; i < children.length; i++)
                        System.out.println(children[i].toString());


                    for(final CandidateProgram c: children) {
                        if(newPopulation.size() < populationSize) {
                            newPopulation.add(c);
                        }
                    }
                }

            }

            updateBestProgram(newPopulation);

            gen++;
        }

        return this.bestProgram.toString();
    }

    private void updateBestProgram(List<CandidateProgram> population) throws Exception {
        for(final CandidateProgram program: population) {
            final double fitness = getFitness(program);
            if(fitness > bestFitness) {
                bestFitness = fitness;
                bestProgram = program;

                Pair<CandidateProgram, Double> p = new Pair<>(bestProgram, bestFitness);
                log.add(p);
            }
        }
    }

    private double getFitness(CandidateProgram candidateProgram) throws Exception {
        String program = candidateProgram.toString();

        String[] options = splitOptions(program);
        String name = options[0];
        options[0] = "";

        Classifier classifier = AbstractClassifier.forName(name, options);

        if(classifier.getCapabilities().test(instances)) {
            classifier.buildClassifier(instances);

            Evaluation evaluation = new Evaluation(instances);
            evaluation.crossValidateModel(classifier, instances, noFolds, new Random(seed));

            double fitness = evaluation.weightedFMeasure();

            if(Double.isNaN(fitness))
                return 0;
            else
                return fitness;

        } else
            return 0;
    }

    private void updateFitness(List<CandidateProgram> population) throws Exception {
        for(int i = 0; i < population.size(); i++) {
            double fitness = getFitness(population.get(i));
            ((GRCandidateProgram) population.get(i)).setFitnessValue(fitness);
        }
    }

    private List<CandidateProgram> elitism(List<CandidateProgram> population) throws Exception {
        List<CandidateProgram> elites;

        updateFitness(population);

        Collections.sort(population);
        elites = new ArrayList<>(population.subList(population.size() - noElites, population.size()));

        return elites;
    }

    private CandidateProgram tournamentSelector(List<CandidateProgram> population) {
        List<CandidateProgram> candidates = new ArrayList<>();

        for(int i = 0; i < this.tournamentSize; i++) {
            int index = (int) Math.round(this.rng.nextDouble() * (population.size() - 1));
            candidates.add((population.get(index)));
        }

        Collections.sort(candidates);

        return candidates.get(0);
    }

    private CandidateProgram[] crossover(List<CandidateProgram> population) {
        CandidateProgram[] children;

        CandidateProgram parent1 = tournamentSelector(population);
        CandidateProgram parent2 = tournamentSelector(population);

        System.out.println("parents:");
        System.out.println(parent1.toString());
        System.out.println(parent2.toString());

        do {
            children = crossover.crossover(parent1.clone(), parent2.clone());
        }while(children == null);

        return children;
    }

    private CandidateProgram mutation(CandidateProgram parent) {
        CandidateProgram child;

        do {
            child = mutation.mutate(parent.clone());
        }while(child == null);


        return child;
    }
}