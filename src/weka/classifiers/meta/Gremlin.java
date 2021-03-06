package weka.classifiers.meta;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.J48;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

import gremlin.Set;
import gremlin.GeneticProgramming;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static weka.core.Utils.getFlag;
import static weka.core.Utils.getOption;
import static weka.core.Utils.splitOptions;

/**
 * Main class to run the Gremlin Classifier.
 */
class Gremlin extends AbstractClassifier {

    private static final long serialVersionUID = -8818333754611517391L;

    private long seed;

    private Classifier classifier;

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

    private boolean printLog;

    /**
     * Main method, used by Weka to run this classifier.
     * @param args
     */
    public static void main(String[] args) {
        AbstractClassifier.runClassifier(new Gremlin(), args);
    }

    /**
     * Builds the classifier.
     * @param instances
     * @throws Exception
     */
    @Override
    public void buildClassifier(Instances instances) throws Exception {
        long start = System.nanoTime();

        /**
         * Initialises the evolutionary process.
         */
        GeneticProgramming gp = new GeneticProgramming(instances, seed, grammarPath, populationSize, noGenerations,
                noElites, initialDepth, maxDepth, tournamentSize, noFolds, crossoverProbability, mutationProbability);

        /**
         * Run the genetic programming and gets the selected individual.
         */
        String program = gp.run();

        double elapsedTime = (System.nanoTime() - start)/1e9;

        /**
         * Splits the individual in name + options to build the classifier.
         */
        String[] options = splitOptions(program);
        String name = options[0];
        options[0] = "";

        classifier = AbstractClassifier.forName(name, options);
//        classifier = new J48();
//        classifier = new ZeroR();
        classifier.buildClassifier(instances);

        if(printLog)
            printLog(gp.getLog(), elapsedTime);
    }

    /**
     *
     */
    public void printLog(List<Set<String, Double>> log, double time) throws IOException {
        PrintWriter writer = new PrintWriter("log.csv", "UTF-8");
        writer.print("Elapse time: ");
        writer.println(String.valueOf(time));
        writer.println("pop: " + String.valueOf(populationSize));
        writer.println("gen: " + String.valueOf(noGenerations));

        for(int i = 0; i < log.size(); i++)
            writer.println(i + ", " + log.get(i).getValue() + ", " + log.get(i).getKey());

        writer.close();
    }

    /**
     * Classifies an instance.
     * @param instance
     * @return
     * @throws Exception
     */
    @Override
    public double classifyInstance(Instance instance) throws Exception {
        return classifier.classifyInstance(instance);
    }

    /**
     *
     * @param instance
     * @return
     * @throws Exception
     */
    @Override
    public double[] distributionForInstance(Instance instance) throws Exception {
        return classifier.distributionForInstance(instance);
    }

    /**
     * Returns classifier's capabilities.
     * @return
     */
    @Override
    public Capabilities getCapabilities() {
        return classifier.getCapabilities();
    }

    /**
     * Sets Gremlin's special options.
     * @param options
     * @throws Exception
     */
    @Override
    public void setOptions(String[] options) throws Exception {
        String auxiliar;

        auxiliar = getOption("seed", options);
        if(auxiliar.length() != 0)
            seed = Integer.parseInt(auxiliar);
        else
            seed = 1;

        auxiliar = getOption("grammar-path", options);
        if(auxiliar.length() != 0)
            grammarPath = auxiliar;
        else
            grammarPath = "grammar/hardcode.bnf";

        auxiliar = getOption("pop-size", options);
        if(auxiliar.length() != 0)
            populationSize = Integer.parseInt(auxiliar);
        else
            populationSize = 50;

        auxiliar = getOption("generations", options);
        if(auxiliar.length() != 0)
            noGenerations = Integer.parseInt(auxiliar);
        else
            noGenerations = 50;

        auxiliar = getOption("no-elites", options);
        if(auxiliar.length() != 0)
            noElites = Integer.parseInt(auxiliar);
        else
            noElites = 1;

        auxiliar = getOption("initial-depth", options);
        if(auxiliar.length() != 0)
            initialDepth = Integer.parseInt(auxiliar);
        else
            initialDepth = 4;

        auxiliar = getOption("max-depth", options);
        if(auxiliar.length() != 0)
            maxDepth = Integer.parseInt(auxiliar);
        else
            maxDepth = 10;

        auxiliar = getOption("tournament-size", options);
        if(auxiliar.length() != 0)
            tournamentSize = Integer.parseInt(auxiliar);
        else
            tournamentSize = 5;

        auxiliar = getOption("no-folds", options);
        if(auxiliar.length() != 0)
            noFolds = Integer.parseInt(auxiliar);
        else
            noFolds = 10;

        auxiliar = getOption("crossover-prob", options);
        if(auxiliar.length() != 0)
            crossoverProbability = Double.parseDouble(auxiliar);
        else
            crossoverProbability = 0.9D;

        auxiliar = getOption("mutation-prob", options);
        if(auxiliar.length() != 0)
            mutationProbability = Double.parseDouble(auxiliar);
        else
            mutationProbability = 0.6D;

        printLog = getFlag("print-log", options);

        super.setOptions(options);
    }

    public String getGrammarPath() {
        return grammarPath;
    }

    public void setGrammarPath(String grammarPath) {
        this.grammarPath = grammarPath;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getNoGenerations() {
        return noGenerations;
    }

    public void setNoGenerations(int noGenerations) {
        this.noGenerations = noGenerations;
    }

    public int getNoElites() {
        return noElites;
    }

    public void setNoElites(int noElites) {
        this.noElites = noElites;
    }

    public int getInitialDepth() {
        return initialDepth;
    }

    public void setInitialDepth(int initialDepth) {
        this.initialDepth = initialDepth;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public int getNoFolds() {
        return noFolds;
    }

    public void setNoFolds(int noFolds) {
        this.noFolds = noFolds;
    }

    public double getCrossoverProbability() {
        return crossoverProbability;
    }

    public void setCrossoverProbability(double crossoverProbability) {
        this.crossoverProbability = crossoverProbability;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public void setMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }
}