package gremlin.grammar;

import gremlin.grammar.base.*;
import gremlin.grammar.ensemble.*;
import gremlin.grammar.meta.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String completeGrammar(String str) {
        if(contains(str, "pre")) {
            str = str.replaceFirst(toRegex("pre"), "");
            str = new AttributeSelectedClassifier().complete(str);
        }
        else if(contains(str, "base")) {
            str = str.replaceFirst(toRegex("base"), "");
            str = completeBase(str);
        }
        else if(contains(str, "meta")) {
            str = str.replaceFirst(toRegex("meta"), "");
            str = completeMeta(str);
        }
        else if(contains(str, "ensemble")) {
            str = str.replaceFirst(toRegex("ensemble"), "");
            str = completeEnsemble(str);
        }

        return str;
    }

    public static String completeBase(String str) {
        if(contains(str, "BayesNet"))
            str = new BayesNet().complete(str);
        else if(contains(str, "DecisionStump"))
            str = new DecisionStump().complete(str);
        else if(contains(str, "DecisionTable"))
            str = new DecisionTable().complete(str);
        else if(contains(str, "IBk"))
            str = new IBk().complete(str);
        else if(contains(str, "J48"))
            str = new J48().complete(str);
        else if(contains(str, "JRip"))
            str = new JRip().complete(str);
        else if(contains(str, "KStar"))
            str = new KStar().complete(str);
        else if(contains(str, "LMT"))
            str = new LMT().complete(str);
        else if(contains(str, "Logistic"))
            str = new Logistic().complete(str);
        else if(contains(str, "MultilayerPerceptron"))
            str = new MultilayerPerceptron().complete(str);
        else if(contains(str, "NaiveBayes"))
            str = new NaiveBayes().complete(str);
        else if(contains(str, "NaiveBayesMultinomial"))
            str = new NaiveBayesMultinomial().complete(str);
        else if(contains(str, "OneR"))
            str = new OneR().complete(str);
        else if(contains(str, "PART"))
            str = new PART().complete(str);
        else if(contains(str, "RandomForest"))
            str = new RandomForest().complete(str);
        else if(contains(str, "RandomTree"))
            str = new RandomTree().complete(str);
        else if(contains(str, "REPTree"))
            str = new REPTree().complete(str);
//        else if(contains(str, "SGD"))   // retirado da gramática por falta de suporte
//            str = new SGD().complete(str);
        else if(contains(str, "SimpleLogistic"))
            str = new SimpleLogistic().complete(str);
        else if(contains(str, "SMO"))
            str = new SMO().complete(str);
//        else if(contains(str, "VotedPerceptron"))   // retirado da gramática por falta de suporte
//            str = new VotedPerceptron().complete(str);
        else if(contains(str, "ZeroR"))
            str = new ZeroR().complete(str);

        return str;
    }

    public static String completeMeta(String str) {
        if(contains(str, "LWL"))
            str = new LWL().complete(str);
        else if(contains(str, "AdaBoostM1"))
            str = new AdaBoostM1().complete(str);
        else if(contains(str, "Bagging"))
            str = new Bagging().complete(str);
        else if(contains(str, "RandomCommittee"))
            str = new RandomCommittee().complete(str);
        else if(contains(str, "RandomSubSpace"))
            str = new RandomSubSpace().complete(str);

        return str;
    }

    public static String completeEnsemble(String str) {
        if(contains(str, "Stacking"))
            str = new Stacking().complete(str);
        else if(contains(str, "Vote"))
            str = new Vote().complete(str);

        return str;
    }

    public static String toRegex(String source) {
        return "\\b" + source + "\\b";
    }

    public static boolean contains(String source, String substring) {
        Pattern pattern = Pattern.compile(toRegex(substring));
        Matcher matcher = pattern.matcher(source);

        return matcher.find();
    }
}
