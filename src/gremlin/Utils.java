package gremlin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String completeConfiguration(String str) {
        if(contains(str, "pre")) {
            str = str.replaceFirst(toRegex("pre"), "");
            str = completePreprocess(str);
        }
        else if(contains(str, "meta")) {
            str = str.replaceFirst(toRegex("meta"), "");
            str = completeMetaClassifier(str);
        }
        else if(contains(str, "base")) {
            str = str.replaceFirst(toRegex("base"), "");
            str = completeBaseClassifier(str);
        }
        else if(contains(str, "ensemb")) {
            str = str.replaceFirst(toRegex("ensemb"), "");
            str = completeEnsemble(str);
        }

        return str;
    }

    public static String completePreprocess(String str) {
        str = Meta.completeAttributeSC(str);

        return str;
    }

    public static String completeMetaClassifier(String str) {
        if(contains(str, "LWL"))
            str = Meta.completeLWL(str);
        else if(contains(str, "AdaBoostM1"))
            str = Meta.completeAdaBM1(str);
        else if(contains(str, "Bagging"))
            str = Meta.completeBagging(str);
        else if(contains(str, "RandomCommittee"))
            str = Meta.completeRandomCom(str);
        else if(contains(str, "RandomSubSpace"))
            str = Meta.completeRandomSS(str);

        return str;
    }

    public static String completeBaseClassifier(String str) {
        if(contains(str, "BayesNet"))
            str = Base.completeBayesNet(str);
        else if(contains(str, "NaiveBayes"))
            str = Base.completeNaiveBayes(str);
        else if(contains(str, "NaiveBayesMultinomial"))
            str = Base.completeNaiveBayesM(str);
        else if(contains(str, "Logistic"))
            str = Base.completeLogistic(str);
        else if(contains(str, "MultilayerPerceptron"))
            str = Base.completeMLP(str);
        else if(contains(str, "SGD"))
            str = Base.completeSGD(str);
        else if(contains(str, "SimpleLogistic"))
            str = Base.completeSL(str);
        else if(contains(str, "SMO"))
            str = Base.completeSMO(str);
        else if(contains(str, "VotedPerceptron"))
            str = Base.completeVotedP(str);
        else if(contains(str, "IBk"))
            str = Base.completeIBk(str);
        else if(contains(str, "KStar"))
            str = Base.completeKStar(str);
        else if(contains(str, "DecisionTable"))
            str = Base.completeDecisionT(str);
        else if(contains(str, "JRip"))
            str = Base.completeJRip(str);
        else if(contains(str, "OneR"))
            str = Base.completeOneR(str);
        else if(contains(str, "PART"))
            str = Base.completePART(str);
        else if(contains(str, "ZeroR"))
            str = Base.completeZeroR(str);
        else if(contains(str, "DecisionStump"))
            str = Base.completeDecisionS(str);
        else if(contains(str, "J48"))
            str = Base.completeJ48(str);
        else if(contains(str, "LMT"))
            str = Base.completeLMT(str);
        else if(contains(str, "RandomForest"))
            str = Base.completeRandomF(str);
        else if(contains(str, "RandomTree"))
            str = Base.completeRandomT(str);
        else if(contains(str, "REPTree"))
            str = Base.completeREPT(str);

        return str;
    }

    public static String completeEnsemble(String str) {
        if(contains(str, "Stacking"))
            str = Ensemble.completeStacking(str);
        else if(contains(str, "Vote"))
            str = Ensemble.completeVote(str);

        return str;
    }

    public static String toRegex(String source) {
        return "\\b" + source + "\\b";
    }

    public static boolean contains(String source, String substring) {
        String toFind = toRegex(substring);
        Pattern pattern = Pattern.compile(toFind);
        Matcher matcher = pattern.matcher(source);

        return matcher.find();
    }
}
