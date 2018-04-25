package weka.classifiers.meta;

import gremlin.GeneticProgramming;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

import static gremlin.grammar.Utils.completeGrammar;
import static weka.core.Utils.getOption;
import static weka.core.Utils.splitOptions;

public class Gremlin extends AbstractClassifier {

    private static final long serialVersionUID = 337915604324006601L;

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

    public static void main(String[] args) {
        AbstractClassifier.runClassifier(new Gremlin(), args);
    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {
        GeneticProgramming gp = new GeneticProgramming();

        gp.init(instances, grammarPath, populationSize, noGenerations, noElites, initialDepth, maxDepth,
                crossoverProbability, mutationProbability, tournamentSize, noFolds);

        String program = gp.run();
        program = completeGrammar(program);

        String[] options = splitOptions(program);
        String name = options[0];
        options[0] = "";

        classifier = AbstractClassifier.forName(name, options);
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
    public Capabilities getCapabilities() {
        return classifier.getCapabilities();
    }

    @Override
    public void setOptions(String[] options) throws Exception {
        String auxiliar;

        auxiliar = getOption("grammar-path", options);
        if(auxiliar.length() != 0)
            grammarPath = auxiliar;
        else
            grammarPath = "grammar/grammar.bnf";

        auxiliar = getOption("pop-size", options);
        if(auxiliar.length() != 0)
            populationSize = Integer.parseInt(auxiliar);
        else
            populationSize = 100;

        auxiliar = getOption("no-generations", options);
        if(auxiliar.length() != 0)
            noGenerations = Integer.parseInt(auxiliar);
        else
            noGenerations = 50;

        auxiliar = getOption("no-elites", options);
        if(auxiliar.length() != 0)
            noElites = Integer.parseInt(auxiliar);
        else
            noElites = 10;

        auxiliar = getOption("initial-depth", options);
        if(auxiliar.length() != 0)
            initialDepth = Integer.parseInt(auxiliar);
        else
            initialDepth = 4;

        auxiliar = getOption("max-depth", options);
        if(auxiliar.length() != 0)
            maxDepth = Integer.parseInt(auxiliar);
        else
            maxDepth = 4;

        auxiliar = getOption("tournament-size", options);
        if(auxiliar.length() != 0)
            tournamentSize = Integer.parseInt(auxiliar);
        else
            tournamentSize = 3;

        auxiliar = getOption("no-folds", options);
        if(auxiliar.length() != 0)
            noFolds = Integer.parseInt(auxiliar);
        else
            noFolds = 10;

        auxiliar = getOption("crossover-prob", options);
        if(auxiliar.length() != 0)
            crossoverProbability = Double.parseDouble(auxiliar);
        else
            crossoverProbability = 0.7D;

        auxiliar = getOption("mutation-prob", options);
        if(auxiliar.length() != 0)
            mutationProbability = Double.parseDouble(auxiliar);
        else
            mutationProbability = 0.3D;

        super.setOptions(options);
    }
}
