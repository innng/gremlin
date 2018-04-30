package gremlin;

import org.epochx.gr.op.crossover.WhighamCrossover;
import org.epochx.gr.op.init.RampedHalfAndHalfInitialiser;
import org.epochx.gr.op.mutation.WhighamMutation;
import org.epochx.op.Crossover;
import org.epochx.op.Initialiser;
import org.epochx.op.Mutation;
import org.epochx.representation.CandidateProgram;
import org.epochx.tools.grammar.Grammar;
import org.epochx.tools.random.MersenneTwisterFast;
import org.epochx.tools.random.RandomNumberGenerator;
import weka.classifiers.Classifier;
import weka.core.Instances;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public String run() {
        this.bestProgram = null;
        this.bestFitness = 0;

//        return this.bestProgram.toString();
        return "J48";
    }

    public void updateBestProgram(List<CandidateProgram> population) {

    }

    public double getFitness(CandidateProgram program) {
        return 0;
    }
}