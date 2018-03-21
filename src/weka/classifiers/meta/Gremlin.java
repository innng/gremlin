package weka.classifiers.meta;

import org.epochx.gr.model.GRModel;
import org.epochx.life.GenerationAdapter;
import org.epochx.life.Life;
import org.epochx.representation.CandidateProgram;
import org.epochx.stats.Stats;
import org.epochx.tools.grammar.Grammar;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Random;

import static gremlin.Utils.completeConfiguration;
import static org.epochx.stats.StatField.*;

public class Gremlin extends AbstractClassifier {

    public class GremlinGR extends GRModel implements Serializable {

        private static final long serialVersionUID = -5998929814656483719L;

        @Override
        public double getFitness(CandidateProgram candidateProgram) {
            String candidate = candidateProgram.toString();
            candidate = completeConfiguration(candidate);

            String[] options = null;

            try {
                assert candidate != null;
                options = Utils.splitOptions(candidate);
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert options != null;
            String name = options[0];
            options[0] = "";

            Classifier individual = null;
            Evaluation evaluation = null;

            try {
                individual = AbstractClassifier.forName(name, options);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(individual.getCapabilities().test(trainInstance)) {
                try {
                    individual.buildClassifier(trainInstance);
                    evaluation = new Evaluation(trainInstance);
                    evaluation.crossValidateModel(individual, trainInstance, crossValidationFolds, new Random());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                return evaluation.weightedFMeasure();
            }
            else {

                return 0.0;
            }
        }

    }

    private static final long serialVersionUID = 337915604324006601L;
    private long seed = 1;

    private GremlinGR grModel = new GremlinGR();

    private Classifier classifier;
    private Instances trainInstance;
    private Instances testInstance;

    private String grammarPath;

    private int tournamentSize;
    private int crossValidationFolds;

    public static void main(String[] args) {
        runClassifier(new Gremlin(args), args);
    }

    public Gremlin(String[] args) {
        grammarPath = "grammar/basic.bnf";
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
        grModel.setReproductionProbability(1.0);
        grModel.setMutationProbability(0.3);

        classifier = null;

        int trainPathIndex = -1, testPathIndex = -1;

        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-t"))
                trainPathIndex = i + 1;
            if(args[i].equals("-T"))
                testPathIndex = i + 1;
        }

        try {
            if(trainPathIndex != -1)
                trainInstance = openInstance(args, trainPathIndex);

            if(testPathIndex != -1)
                testInstance = openInstance(args, testPathIndex);
            grModel.setGrammar(openGrammar());
        } catch (Exception e) {
            e.printStackTrace();
        }


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

        CandidateProgram candidateProgram = (CandidateProgram) Stats.get().getStat(RUN_FITTEST_PROGRAM);
        String candidate = candidateProgram.toString();
//        candidate = completeConfiguration(candidate);
//
//        String[] options = null;
//
//        try {
//            options = Utils.splitOptions(candidate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        assert options != null;
//        String name = options[0];
//        options[0] = "";
//
//        classifier = forName(name, options);
//        classifier.buildClassifier(trainInstance);

        System.out.println(candidate);
        classifier = new J48();
        classifier.buildClassifier(trainInstance);
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
        return classifier.getCapabilities();
    }

    @Override
    public String toString() {
        return classifier.toString();
    }

    public Grammar openGrammar() throws IOException {
        File grammarFile = new File(grammarPath);
        Grammar grammar = new Grammar(grammarFile);

        return grammar;
    }

    public Instances openInstance(String[] args, int index) throws Exception {
        String dataPath = args[index];
        DataSource source = new DataSource(dataPath);
        Instances data = source.getDataSet();

        if(data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }

        return data;
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
        this.classifier = classifier;
    }

    public Instances getTrainInstance() {
        return trainInstance;
    }

    public void setTrainInstance(Instances trainInstance) {
        this.trainInstance = trainInstance;
    }

    public Instances getTestInstance() {
        return testInstance;
    }

    public void setTestInstance(Instances testInstance) {
        this.testInstance = testInstance;
    }

    public String getGrammarPath() {
        return grammarPath;
    }

    public void setGrammarPath(String grammarPath) {
        this.grammarPath = grammarPath;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public int getCrossValidationFolds() {
        return crossValidationFolds;
    }

    public void setCrossValidationFolds(int crossValidationFolds) {
        this.crossValidationFolds = crossValidationFolds;
    }
}