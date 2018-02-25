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
import weka.classifiers.trees.J48;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Option;

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
            String candidate = candidateProgram.toString();

            String[] auxiliarSplit = null;
            try {
                auxiliarSplit = splitOptions(candidate);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String name = auxiliarSplit[0];
            String[] options = null;
            for(int i = 1; i < auxiliarSplit.length; i++)
                options[i - 1] = auxiliarSplit[i];

            Classifier individual = null;
            Evaluation eval = null;
            try {
                individual = AbstractClassifier.forName(name, options);
                individual.buildClassifier(trainFile);
                eval = new Evaluation(trainFile);
                eval.crossValidateModel(individual, trainFile, crossValidationFolds, trainFile.getRandomNumberGenerator(1), seed);
            } catch (Exception e) {
                e.printStackTrace();
            }

            double fMeasure = eval.weightedFMeasure();
            return fMeasure;
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

    private Gremlin() {
        if(trainFile.classIndex() == -1)
            trainFile.setClassIndex(trainFile.numAttributes() - 1);

        if(testFile.classIndex() == -1)
            testFile.setClassIndex(testFile.numAttributes() - 1);
    }

    public static void main(String[] args) {
        AbstractClassifier.runClassifier(new Gremlin(), args);
    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {
        grammarPath = "E:\\UbuntuBashFiles\\git\\gremlin\\grammar\\backup.bnf";
        tournamentSize = 3;

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

    private String getGrammarPath() { return grammarPath; }

    private void setGrammarPath(String grammarPath) {
        if(grammarPath != null)
            this.grammarPath = grammarPath;
    }

    private String getTrainPath() { return trainPath; }

    private void setTrainPath(String trainPath) {
        if(trainPath != null)
            this.trainPath = trainPath;
    }

    private String getTestPath() { return testPath; }

    private void setTestPath(String testPath) {
        if(testPath != null)
            this.testPath = testPath;
    }

    private int getTournamentSize() { return tournamentSize; }

    private void setTournamentSize(int tournamentSize) {
        if(tournamentSize > 0 && tournamentSize <= grModel.getPopulationSize())
            this.tournamentSize = tournamentSize;
    }
}