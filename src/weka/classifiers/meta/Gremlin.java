package weka.classifiers.meta;

import org.epochx.gr.model.GRModel;
import org.epochx.gr.op.crossover.WhighamCrossover;
import org.epochx.gr.op.init.RampedHalfAndHalfInitialiser;
import org.epochx.gr.op.mutation.WhighamMutation;
import org.epochx.life.GenerationAdapter;
import org.epochx.life.Life;
import org.epochx.op.selection.TournamentSelector;
import org.epochx.representation.CandidateProgram;
import org.epochx.stats.Stats;
import org.epochx.tools.grammar.Grammar;
import org.epochx.tools.random.JavaRandom;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Option;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;
import java.io.Serializable;
import java.util.Enumeration;

import static org.epochx.stats.StatField.*;
import static weka.core.Utils.splitOptions;

class Gremlin extends AbstractClassifier {

    private class GR extends GRModel implements Serializable {

        private static final long serialVersionUID = 3300306574396301033L;

        @Override
        public double getFitness(CandidateProgram candidateProgram) {
//            String candidate = candidateProgram.toString();
//
//            String[] auxiliarSplit = null;
//            try {
//                auxiliarSplit = splitOptions(candidate);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            assert auxiliarSplit != null;
//            String name = auxiliarSplit[0];
//
//            String[] options = null;
//
//            System.arraycopy(auxiliarSplit, 1, options, 0, auxiliarSplit.length - 1);
//
//            double fMeasure = 0;
//            try {
//                fMeasure = execute(name, options);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return fMeasure;

            return 0;
        }
    }

    private static final long serialVersionUID = -9095568595070708413L;
    private long seed = 123;

    private Classifier classifier;
    private Instances trainFile;
    private Instances testFile;
    private Evaluation evaluation;

    private String trainPath;
    private String testPath;

    private String grammarPath;

    private int tournamentSize;
    private int crossValidationFolds;

    private GR grModel = new GR();

    public Gremlin(String[] args) throws Exception {
        int trainPathIndex = -1, testPathIndex = -1;
        DataSource source;

        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-t"))
                trainPathIndex = i + 1;
            if(args[i].equals("-T"))
                testPathIndex = i + 1;
        }

        if(trainPathIndex != -1) {
            trainPath = args[trainPathIndex];
            source = new DataSource(trainPath);
            trainFile = source.getDataSet();

            if(trainFile.classIndex() == -1)
                trainFile.setClassIndex(trainFile.numAttributes() - 1);
        }
        if(testPathIndex != -1) {
            testPath = args[testPathIndex];
            source = new DataSource(testPath);
            testFile = source.getDataSet();

            if(testFile.classIndex() == -1)
                testFile.setClassIndex(testFile.numAttributes() - 1);
        }

    }

    public static void main(String[] args) throws Exception {
        AbstractClassifier.runClassifier(new Gremlin(args), args);
    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {
        setGrammarPath("E:\\UbuntuBashFiles\\git\\gremlin\\grammar\\grammar.bnf");
        setTournamentSize(3);

        File grammarFile = new File(grammarPath);

        grModel.setGrammar(new Grammar(grammarFile));

        grModel.setMaxDepth(5);
        grModel.setMaxInitialDepth(6);

        grModel.setProgramSelector(new TournamentSelector(grModel, getTournamentSize()));
        grModel.setRNG(new JavaRandom());

        grModel.setInitialiser(new RampedHalfAndHalfInitialiser(grModel));
        grModel.setCrossover(new WhighamCrossover(grModel));
        grModel.setMutation(new WhighamMutation(grModel));


        grModel.setNoRuns(1);
        grModel.setNoGenerations(50);
        grModel.setPopulationSize(100);
        grModel.setPoolSize(50);
        grModel.setNoElites(10);

        grModel.setTerminationFitness(1.0);
        grModel.setCrossoverProbability(0.7);
        grModel.setMutationProbability(0.3);
        grModel.setReproductionProbability(1.0);

        Life.get().addGenerationListener(new GenerationAdapter() {
            @Override
            public void onGenerationEnd() {
                Stats.get().print(GEN_NUMBER, GEN_FITNESS_MIN, GEN_FITTEST_PROGRAM);
            }
        });

        grModel.run();

//        classifier = new J48();
//        classifier.buildClassifier(instances);
    }



    public String globalInfo() {
        return "Has yet to be defined.";
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
    public Enumeration<Option> listOptions() {
        return super.listOptions();
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

    public double execute(String name, String[] options) throws Exception {
        Classifier individual = AbstractClassifier.forName(name, options);
        individual.buildClassifier(trainFile);
        Evaluation eval = new Evaluation(trainFile);
        eval.crossValidateModel(individual, trainFile, crossValidationFolds, trainFile.getRandomNumberGenerator(1), seed);

        return eval.weightedFMeasure();
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        if(classifier != null)
            this.classifier = classifier;
    }

    public Instances getTrainFile() {
        return trainFile;
    }

    public void setTrainFile(Instances trainFile) {
        if(trainFile != null)
            this.trainFile = trainFile;
    }

    public Instances getTestFile() {
        return testFile;
    }

    public void setTestFile(Instances testFile) {
        if(testFile != null)
            this.testFile = testFile;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        if(evaluation != null)
            this.evaluation = evaluation;
    }

    public String getGrammarPath() {
        return grammarPath;
    }

    public void setGrammarPath(String grammarPath) {
        if(grammarPath != null)
            this.grammarPath = grammarPath;
    }

    public String getTrainPath() {
        return trainPath;
    }

    public void setTrainPath(String trainPath) {
        if(trainPath != null)
            this.trainPath = trainPath;
    }

    public String getTestPath() {
        return testPath;
    }

    public void setTestPath(String testPath) {
        if(testPath != null)
            this.testPath = testPath;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        if(tournamentSize > 0 && tournamentSize <= grModel.getPopulationSize())
            this.tournamentSize = tournamentSize;
    }

    public int getCrossValidationFolds() {
        return crossValidationFolds;
    }

    public void setCrossValidationFolds(int crossValidationFolds) {
        if(crossValidationFolds > 0)
            this.crossValidationFolds = crossValidationFolds;
    }
}