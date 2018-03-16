package weka.classifiers.meta;

import gremlin.Utils;
import org.epochx.gr.model.GRModel;
import org.epochx.gr.op.crossover.WhighamCrossover;
import org.epochx.gr.op.init.RampedHalfAndHalfInitialiser;
import org.epochx.gr.op.mutation.WhighamMutation;
import org.epochx.life.GenerationAdapter;
import org.epochx.life.Life;
import org.epochx.op.selection.TournamentSelector;
import org.epochx.representation.CandidateProgram;
import org.epochx.stats.Stat;
import org.epochx.stats.Stats;
import org.epochx.tools.grammar.Grammar;
import org.epochx.tools.random.JavaRandom;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Option;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;

import static org.epochx.stats.StatField.*;
import static weka.core.Utils.splitOptions;

public class Gremlin extends AbstractClassifier {

    private class GremlinGR extends GRModel implements Serializable {

        private static final long serialVersionUID = -2246451161244360831L;

        @Override
        public double getFitness(CandidateProgram candidateProgram) {
//            Classifier individual = null;
//            double fMeasure = 0;
//            try {
//                individual = transforms(candidateProgram);
//                fMeasure = execute(individual);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return fMeasure;
            return 0;
        }
    }

    private static final long serialVersionUID = 2388156596868154954L;
    private long seed = 1;

    private Classifier classifier;
    private Instances trainInstance;
    private Instances testInstance;
    private Evaluation evaluation;

    private File grammarFile;

    private String trainPath;
    private String testPath;
    private String grammarPath;

    private int tournamentSize;
    private int crossValidationFolds;

    private GremlinGR grModel = new GremlinGR();

    public Gremlin(String[] args) {
        int trainPathIndex = -1, testPathIndex = -1;

        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-t"))
                trainPathIndex = i + 1;
            if(args[i].equals("-T"))
                testPathIndex = i + 1;
        }

        try {
            if(trainPathIndex != -1)
                initialConfiguration(trainInstance, args, trainPath, trainPathIndex);

            if(testPathIndex != -1)
                initialConfiguration(testInstance, args, testPath, testPathIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }

        classifier = null;
        evaluation = null;

        grammarPath = "grammar/grammar.bnf";
        grammarFile = new File(grammarPath);

        tournamentSize = 3;
        crossValidationFolds = 10;

        grModel.setMaxDepth(5);
        grModel.setMaxInitialDepth(6);

        grModel.setNoRuns(1);
        grModel.setNoGenerations(50);
        grModel.setNoElites(10);
        grModel.setPopulationSize(100);
        grModel.setPoolSize(50);

        grModel.setTerminationFitness(1.0);
        grModel.setCrossoverProbability(0.7);
        grModel.setMutationProbability(0.3);
        grModel.setReproductionProbability(1.0);

        try {
            grModel.setGrammar(new Grammar(grammarFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        grModel.setProgramSelector(new TournamentSelector(grModel, getTournamentSize()));
        grModel.setRNG(new JavaRandom());

        grModel.setInitialiser(new RampedHalfAndHalfInitialiser(grModel));
        grModel.setCrossover(new WhighamCrossover(grModel));
        grModel.setMutation(new WhighamMutation(grModel));
    }

    public static void main(String[] args) {
        AbstractClassifier.runClassifier(new Gremlin(args), args);
        String teste = "meta AdaBoostM1 P 100 I 10 Q FALSE W DecisionStump a";
        System.out.println(teste);
        teste = Utils.completeConfiguration(teste);
        System.out.println(teste);
    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {
        Life.get().addGenerationListener(new GenerationAdapter() {
            @Override
            public void onGenerationEnd() {
                Stats.get().print(GEN_NUMBER, GEN_FITNESS_MIN, GEN_FITTEST_PROGRAM);
            }
        });

        grModel.run();

        CandidateProgram cp = (CandidateProgram) Stats.get().getStat(RUN_FITTEST_PROGRAM);
        String teste = cp.toString();
        System.out.println("aaah" + teste);

        classifier = new J48();
        classifier.buildClassifier(instances);
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

    public void initialConfiguration(Instances data, String[] args, String dataPath, int index) throws Exception {
        dataPath = args[index];
        DataSource source = new DataSource(dataPath);
        data = source.getDataSet();

        if(data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
    }

    public Classifier transforms(CandidateProgram candidateProgram) throws Exception {
        String newClassifier = candidateProgram.toString();
        newClassifier = Utils.completeConfiguration(newClassifier);

        String[] auxiliarSplit;
        auxiliarSplit = splitOptions(newClassifier);
        String name = auxiliarSplit[0];

        String[] options = null;
        System.arraycopy(auxiliarSplit, 1, options, 0, auxiliarSplit.length - 1);

        Classifier classifier = AbstractClassifier.forName(name, options);
        return classifier;
    }

    public double execute(Classifier classifier) throws Exception {
        classifier.buildClassifier(trainInstance);
        evaluation = new Evaluation(trainInstance);
        evaluation.crossValidateModel(classifier, trainInstance, crossValidationFolds, trainInstance.getRandomNumberGenerator(seed), seed);
        return evaluation.weightedFMeasure();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        if(classifier != null)
            this.classifier = classifier;
    }

    public Instances getTrainInstance() {
        return trainInstance;
    }

    public void setTrainInstance(Instances trainInstance) {
        if(trainInstance != null)
            this.trainInstance = trainInstance;
    }

    public Instances getTestInstance() {
        return testInstance;
    }

    public void setTestInstance(Instances testInstance) {
        if(testInstance != null)
            this.testInstance = testInstance;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        if(evaluation != null)
            this.evaluation = evaluation;
    }

    public File getGrammarFile() {
        return grammarFile;
    }

    public void setGrammarFile(File grammarFile) {
        if(grammarFile != null)
            this.grammarFile = grammarFile;
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

    public String getGrammarPath() {
        return grammarPath;
    }

    public void setGrammarPath(String grammarPath) {
        if(grammarPath != null)
            this.grammarPath = grammarPath;
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
        if(crossValidationFolds >= 0)
            this.crossValidationFolds = crossValidationFolds;
    }
}
