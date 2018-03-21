package gremlin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String completeConfiguration(String str) {
        if(contains(str, "pre")) {
            str = str.replaceFirst(toRegex("pre"), "");
            str = Meta.AttributeSelectedClassifier(str);
        }
        else if(contains(str, "meta")) {
            str = str.replaceFirst(toRegex("meta"), "");
            str = metaClassifier(str);
        }
        else if(contains(str, "base")) {
            str = str.replaceFirst(toRegex("base"), "");
            str = baseClassifier(str);
        }

        return str;
    }

    public static String metaClassifier(String str) {
        if(contains(str, "LWL"))
            str = Meta.LWL(str);
        else if(contains(str, "AdaBoostM1"))
            str = Meta.AdaBoostM1(str);
        else if(contains(str, "Bagging"))
            str = Meta.Bagging(str);
        else if(contains(str, "RandomCommittee"))
            str = Meta.RandomCommittee(str);
        else if(contains(str, "RandomSubSpace"))
            str = Meta.RandomSubSpace(str);

        return str;
    }

    public static String baseClassifier(String str) {
        if(contains(str, "BayesNet"))
            str = Base.BayesNet(str);
        else if(contains(str, "NaiveBayes"))
            str = Base.NaiveBayes(str);
        else if(contains(str, "NaiveBayesMultinomial"))
            str = Base.NaiveBayesMultinomial(str);
        else if(contains(str, "Logistic"))
            str = Base.Logistic(str);
        else if(contains(str, "MultilayerPerceptron"))
            str = Base.MultilayerPerceptron(str);
        else if(contains(str, "SGD"))
            str = Base.SGD(str);
        else if(contains(str, "SimpleLogistic"))
            str = Base.SimpleLogistic(str);
        else if(contains(str, "SMO"))
            str = Base.SMO(str);
        else if(contains(str, "VotedPerceptron"))
            str = Base.VotedPerceptron(str);
        else if(contains(str, "IBk"))
            str = Base.IBk(str);
        else if(contains(str, "KStar"))
            str = Base.KStar(str);
        else if(contains(str, "DecisionTable"))
            str = Base.DecisionTable(str);
        else if(contains(str, "JRip"))
            str = Base.JRip(str);
        else if(contains(str, "OneR"))
            str = Base.OneR(str);
        else if(contains(str, "PART"))
            str = Base.PART(str);
        else if(contains(str, "ZeroR"))
            str = Base.ZeroR(str);
        else if(contains(str, "DecisionStump"))
            str = Base.DecisionStump(str);
        else if(contains(str, "J48"))
            str = Base.J48(str);
        else if(contains(str, "LMT"))
            str = Base.LMT(str);
        else if(contains(str, "RandomForest"))
            str = Base.RandomForest(str);
        else if(contains(str, "RandomTree"))
            str = Base.RandomTree(str);
        else if(contains(str, "REPTree"))
            str = Base.REPTree(str);

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
