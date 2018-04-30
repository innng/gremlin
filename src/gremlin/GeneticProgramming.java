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

    public String run() throws Exception {
        this.bestProgram = null;
        this.bestFitness = 0;

        List<CandidateProgram> population = initialiser.getInitialPopulation();
        updateBestProgram(population);

        int gen = 1;
        while(gen <= noGenerations) {
            
        }

//        List<CandidateProgram> elitsm = elitsm(population);

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

    private List<CandidateProgram> elitsm(List<CandidateProgram> population) throws Exception {
        List<CandidateProgram> elites = null;

        updateFitness(population);

        Collections.sort(population);
        elites = new ArrayList<>(population.subList(population.size() - noElites, population.size()));

        return elites;
    }
}