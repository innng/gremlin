package ufmg.gremlin;

import org.epochx.gr.model.GRModel;
import org.epochx.life.GenerationAdapter;
import org.epochx.life.Life;
import org.epochx.representation.CandidateProgram;
import org.epochx.stats.Stats;
import org.epochx.tools.grammar.Grammar;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Option;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import static org.epochx.stats.StatField.*;

class GremlinClassifier extends AbstractClassifier {

    protected Classifier classifier;

    protected String grammarPath;

    protected File grammarFile;

    protected GRModel grModel = new GRModel() {

        @Override
        public double getFitness(CandidateProgram candidateProgram) {
//            String individual = candidateProgram.toString();
//            Classifier evaluation = AbstractClassifier.forName();
//            evaluation.buildClassifier();
//            CrossV
            return 0;
        }
    };

    public GremlinClassifier() throws IOException {
        grammarPath = "E:\\UbuntuBashFiles\\git\\gremlin\\grammar\\backup.bnf";
        grammarFile = new File(grammarPath);

        grModel.setGrammar(new Grammar(grammarFile));

        Life.get().addGenerationListener(new GenerationAdapter() {
            @Override
            public void onGenerationEnd() {
                Stats.get().print(GEN_NUMBER, GEN_FITNESS_MIN, GEN_FITTEST_PROGRAM);
            }
        });

        grModel.run();

    }

    public static void main(String[] args) {
//        AbstractClassifier.runClassifier(new GremlinClassifier(), args);
        try {
            GremlinClassifier g = new GremlinClassifier();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {
//    fmeasure crossvalidation pros indivíduos
//    fmeasure do teste só no melhor indivíduo
    }

    public String globalInfo() {
        return "Has yet to be defined.";
    }

    @Override
    public Enumeration<Option> listOptions() {
        return super.listOptions();
    }

    @Override
    public void setOptions(String[] options) throws Exception {
        super.setOptions(options);
    }

    @Override
    public String[] getOptions() {
        return super.getOptions();
    }

    @Override
    public Capabilities getCapabilities() {
        return super.getCapabilities();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public double[] distributionForInstance(Instance instance) throws Exception {
        return super.distributionForInstance(instance);
    }

    @Override
    public double classifyInstance(Instance instance) throws Exception {
        return super.classifyInstance(instance);
    }
}