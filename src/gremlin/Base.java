package gremlin;

import static gremlin.Utils.contains;
import static gremlin.Utils.toRegex;

public class Base {

    public static String completeBayesNet(String str) {
        str = str.replaceFirst(toRegex("BayesNet"), "weka.classifiers.bayes.BayesNet");
        str = str.replaceFirst(toRegex("Q"), "-Q");

        if(contains(str, "D TRUE"))
            str = str.replaceFirst(toRegex("D TRUE"), "");
        else if(contains(str, "D FALSE"))
            str = str.replaceFirst(toRegex("D FALSE"), "-D");

        if(contains(str, "K2"))
            str = str.replaceFirst(toRegex("K2"), "weka.classifiers.bayes.net.search.local.K2");
        else if(contains(str, "HillClimber"))
            str = str.replaceFirst(toRegex("HillClimber"), "weka.classifiers.bayes.net.search.local.HillClimber");
        else if(contains(str, "LAGDHillClimber"))
            str = str.replaceFirst(toRegex("LAGDHillClimber"), "weka.classifiers.bayes.net.search.local.LAGDHillClimber");
        else if(contains(str, "SimulatedAnnealing"))
            str = str.replaceFirst(toRegex("SimulatedAnnealing"), "weka.classifiers.bayes.net.search.local.SimulatedAnnealing");
        else if(contains(str, "TabuSearch"))
            str = str.replaceFirst(toRegex("TabuSearch"), "weka.classifiers.bayes.net.search.local.TabuSearch");
        else if(contains(str, "TAN"))
            str = str.replaceFirst(toRegex("TAN"), "weka.classifiers.bayes.net.search.local.TAN");

        return str;
    }

    public static String completeNaiveBayes(String str) {
        str = str.replaceFirst(toRegex("NaiveBayes"), "weka.classifiers.bayes.NaiveBayes");

        if(contains(str, "K FALSE") && contains(str, "D FALSE") && contains(str, "--"))
            str = str.replaceFirst(toRegex("--"), "");

        if(contains(str, "D TRUE"))
            str = str.replaceFirst(toRegex("D TRUE"), "-D");
        else if(contains(str, "D FALSE"))
            str = str.replaceFirst(toRegex("D FALSE"), "");

        if(contains(str, "K TRUE"))
            str = str.replaceFirst(toRegex("K TRUE"), "-K");
        else if(contains(str, "K FALSE"))
            str = str.replaceFirst(toRegex("K FALSE"), "");

        return str;
    }

    public static String completeNaiveBayesM(String str) {
        str = str.replaceFirst(toRegex("NaiveBayesMultinomial"), "weka.classifiers.bayes.NaiveBayesMultinomial");

        if(contains(str, "--"))
            str = str.replaceFirst(toRegex("--"), "");

        return str;
    }

    public static String completeLogistic(String str) {
        str = str.replaceFirst(toRegex("Logistic"), "weka.classifiers.functions.Logistic");
        str = str.replaceFirst(toRegex("R"), "-R");

        return str;
    }

    public static String completeMLP(String str) {
        str = str.replaceFirst(toRegex("MultilayerPerceptron"), "weka.classifiers.functions.MultilayerPerceptron");
        str = str.replaceFirst(toRegex("H"), "-H");
        str = str.replaceFirst(toRegex("L"), "-L");
        str = str.replaceFirst(toRegex("M"), "-M");

        if(contains(str, "B TRUE"))
            str = str.replaceFirst(toRegex("B TRUE"), "");
        else if(contains(str, "B FALSE"))
            str = str.replaceFirst(toRegex("B FALSE"), "-B");

        if(contains(str, "C TRUE"))
            str = str.replaceFirst(toRegex("C TRUE"), "");
        else if(contains(str, "C FALSE"))
        str = str.replaceFirst(toRegex("C FALSE"), "-C");

        if(contains(str, "D TRUE"))
            str = str.replaceFirst(toRegex("D TRUE"), "-D");
        else if(contains(str, "D FALSE"))
        str = str.replaceFirst(toRegex("D FALSE"), "");

        if(contains(str, "R TRUE"))
            str = str.replaceFirst(toRegex("R TRUE"), "");
        else if(contains(str, "R FALSE"))
        str = str.replaceFirst(toRegex("R FALSE"), "-R");

        return str;
    }

    public static String completeSGD(String str) {
        str = str.replaceFirst(toRegex("SGD"), "weka.classifiers.functions.SGD");
        str = str.replaceFirst(toRegex("F"), "-F");
        str = str.replaceFirst(toRegex("L"), "-L");
        str = str.replaceFirst(toRegex("R"), "-R");

        if(contains(str, "M TRUE"))
            str = str.replaceFirst(toRegex("M TRUE"), "-M");
        else if(contains(str, "M FALSE"))
            str = str.replaceFirst(toRegex("M FALSE"), "");

        if(contains(str, "N TRUE"))
            str = str.replaceFirst(toRegex("N TRUE"), "-N");
        else if(contains(str, "N FALSE"))
            str = str.replaceFirst(toRegex("N FALSE"), "");

        return str;
    }

    public static String completeSL(String str) {
        str = str.replaceFirst(toRegex("SimpleLogistic"), "weka.classifiers.functions.SimpleLogistic");
        str = str.replaceFirst(toRegex("W"), "-W");

        if(contains(str, "A TRUE"))
            str = str.replaceFirst(toRegex("A TRUE"), "-A");
        else if(contains(str, "A FALSE"))
            str = str.replaceFirst(toRegex("A FALSE"), "");

        if(contains(str, "S TRUE"))
            str = str.replaceFirst(toRegex("S TRUE"), "");
        else if(contains(str, "S FALSE"))
            str = str.replaceFirst(toRegex("S FALSE"), "-S");

        return str;
    }

    public static String completeSMO(String str) {
//        TODO
//         -C 1.0 -L 0.001 -P 1.0E-12 -N 0 -V -1 -W 1 -K "weka.classifiers.functions.supportVector.PolyKernel -E 1.0 -C 250007" -calibrator "weka.classifiers.functions.Logistic -R 1.0E-8 -M -1 -num-decimal-places 4"
        str = str.replaceFirst(toRegex("SMO"), "weka.classifiers.functions.SMO");
        str = str.replaceFirst(toRegex("C"), "-C");
//        str = str.replaceFirst(toRegex("K"), "-K");
        str = str.replaceFirst(toRegex("N"), "-N");

        if(contains(str, "M TRUE"))
            str = str.replaceFirst(toRegex("M TRUE"), "-M");
        else if(contains(str, "M FALSE"))
            str = str.replaceFirst(toRegex("M FALSE"), "");

        return str;
    }

    public static String completeVotedP(String str) {
        str = str.replaceFirst(toRegex("VotedPerceptron"), "weka.classifiers.functions.VotedPerceptron");
        str = str.replaceFirst(toRegex("E"), "-E");
        str = str.replaceFirst(toRegex("I"), "-I");
        str = str.replaceFirst(toRegex("M"), "-M");

        return str;
    }

    public static String completeIBk(String str) {
        str = str.replaceFirst(toRegex("IBk"), "weka.classifiers.lazy.IBk");
        str = str.replaceFirst(toRegex("K"), "-K");

        if(contains(str, "E TRUE"))
            str = str.replaceFirst(toRegex("E TRUE"), "-E");
        else if(contains(str, "E FALSE"))
            str = str.replaceFirst(toRegex("E FALSE"), "");

        if(contains(str, "X TRUE"))
            str = str.replaceFirst(toRegex("X TRUE"), "-X");
        else if(contains(str, "X FALSE"))
            str = str.replaceFirst(toRegex("X FALSE"), "");

        if(contains(str, "IF I"))
            str = str.replaceFirst(toRegex("IF I"), "-I");
        else if(contains(str, "IF F"))
            str = str.replaceFirst(toRegex("IF F"), "-F");
        else if(contains(str, "IF FALSE"))
            str = str.replaceFirst(toRegex("IF FALSE"), "");

        return str;
    }

    public static String completeKStar(String str) {
        str = str.replaceFirst(toRegex("KStar"), "weka.classifiers.lazy.KStar");
        str = str.replaceFirst(toRegex("B"), "-B");
        str = str.replaceFirst(toRegex("M"), "-M");

        if(contains(str, "E TRUE"))
            str = str.replaceFirst(toRegex("E TRUE"), "-E");
        else if(contains(str, "E FALSE"))
            str = str.replaceFirst(toRegex("E FALSE"), "");

        return str;
    }

    public static String completeDecisionT(String str) {
//        TODO
//         -X 1 -S "weka.attributeSelection.BestFirst -D 1 -N 5"
        str = str.replaceFirst(toRegex("DecisionTable"), "weka.classifiers.rules.DecisionTable");
        str = str.replaceFirst(toRegex("E"), "-E");
//        str = str.replaceFirst(toRegex("S"), "-S");
        str = str.replaceFirst(toRegex("X"), "-X");

        if(contains(str, "I TRUE"))
            str = str.replaceFirst(toRegex("I TRUE"), "-I");
        else if(contains(str, "I FALSE"))
            str = str.replaceFirst(toRegex("I FALSE"), "");

        return str;
    }

    public static String completeJRip(String str) {
        str = str.replaceFirst(toRegex("JRip"), "weka.classifiers.rules.JRip");
        str = str.replaceFirst(toRegex("N"), "-N");
        str = str.replaceFirst(toRegex("O"), "-O");

        if(contains(str, "E TRUE"))
            str = str.replaceFirst(toRegex("E TRUE"), "");
        else if(contains(str, "E FALSE"))
            str = str.replaceFirst(toRegex("E FALSE"), "-E");

        if(contains(str, "P TRUE"))
            str = str.replaceFirst(toRegex("P TRUE"), "");
        else if(contains(str, "P FALSE"))
            str = str.replaceFirst(toRegex("P FALSE"), "-P");

        return str;
    }

    public static String completeOneR(String str) {
        str = str.replaceFirst(toRegex("OneR"), "weka.classifiers.rules.OneR");
        str = str.replaceFirst(toRegex("B"), "-B");

        return str;
    }

    public static String completePART(String str) {
        str = str.replaceFirst(toRegex("PART"), "weka.classifiers.rules.PART");
        str = str.replaceFirst(toRegex("M"), "-M");
        str = str.replaceFirst(toRegex("N"), "-N");

        if(contains(str, "B TRUE"))
            str = str.replaceFirst(toRegex("B TRUE"), "-B");
        else if(contains(str, "B FALSE"))
            str = str.replaceFirst(toRegex("B FALSE"), "");

        if(contains(str, "R TRUE"))
            str = str.replaceFirst(toRegex("R TRUE"), "-R");
        else if(contains(str, "R FALSE"))
            str = str.replaceFirst(toRegex("R FALSE"), "");

        return str;
    }

    public static String completeZeroR(String str) {
        str = str.replaceFirst(toRegex("ZeroR"), "weka.classifiers.rules.ZeroR");

        if(contains(str, "--"))
            str = str.replaceFirst(toRegex("--"), "");

        return str;
    }

    public static String completeDecisionS(String str) {
        str = str.replaceFirst(toRegex("DecisionStump"), "weka.classifiers.trees.DecisionStump");

        if(contains(str, "--"))
            str = str.replaceFirst(toRegex("--"), "");

        return str;
    }

    public static String completeJ48(String str) {
        str = str.replaceFirst(toRegex("J48"), "weka.classifiers.trees.J48");
        str = str.replaceFirst(toRegex("C"), "-C");
        str = str.replaceFirst(toRegex("M"), "-M");

        if(contains(str, "A TRUE"))
            str = str.replaceFirst(toRegex("A TRUE"), "-A");
        else if(contains(str, "A FALSE"))
            str = str.replaceFirst(toRegex("A FALSE"), "");

        if(contains(str, "B TRUE"))
            str = str.replaceFirst(toRegex("B TRUE"), "-B");
        else if(contains(str, "B FALSE"))
            str = str.replaceFirst(toRegex("B FALSE"), "");

        if(contains(str, "J TRUE"))
            str = str.replaceFirst(toRegex("J TRUE"), "");
        else if(contains(str, "J FALSE"))
            str = str.replaceFirst(toRegex("J FALSE"), "-J");

        if(contains(str, "O TRUE"))
            str = str.replaceFirst(toRegex("O TRUE"), "");
        else if(contains(str, "O FALSE"))
            str = str.replaceFirst(toRegex("O FALSE"), "-O");

        if(contains(str, "S TRUE"))
            str = str.replaceFirst(toRegex("S TRUE"), "");
        else if(contains(str, "S FALSE"))
            str = str.replaceFirst(toRegex("S FALSE"), "-S");

        if(contains(str, "U TRUE"))
            str = str.replaceFirst(toRegex("U TRUE"), "-U");
        else if(contains(str, "U FALSE"))
            str = str.replaceFirst(toRegex("U FALSE"), "");

        return str;
    }

    public static String completeLMT(String str) {
        str = str.replaceFirst(toRegex("LMT"), "weka.classifiers.trees.LMT");
        str = str.replaceFirst(toRegex("M"), "-M");
        str = str.replaceFirst(toRegex("W"), "-W");

        if(contains(str, "A TRUE"))
            str = str.replaceFirst(toRegex("A TRUE"), "-A");
        else if(contains(str, "A FALSE"))
            str = str.replaceFirst(toRegex("A FALSE"), "");

        if(contains(str, "B TRUE"))
            str = str.replaceFirst(toRegex("B TRUE"), "-B");
        else if(contains(str, "B FALSE"))
            str = str.replaceFirst(toRegex("B FALSE"), "");

        if(contains(str, "C TRUE"))
            str = str.replaceFirst(toRegex("C TRUE"), "");
        else if(contains(str, "C FALSE"))
            str = str.replaceFirst(toRegex("C FALSE"), "-C");

        if(contains(str, "P TRUE"))
            str = str.replaceFirst(toRegex("P TRUE"), "-P");
        else if(contains(str, "P FALSE"))
            str = str.replaceFirst(toRegex("P FALSE"), "");

        if(contains(str, "R TRUE"))
            str = str.replaceFirst(toRegex("R TRUE"), "-R");
        else if(contains(str, "R FALSE"))
            str = str.replaceFirst(toRegex("R FALSE"), "");

        return str;
    }

    public static String completeRandomF(String str) {
        str = str.replaceFirst(toRegex("RandomForest"), "weka.classifiers.trees.RandomForest");
        str = str.replaceFirst(toRegex("I"), "-I");
        str = str.replaceFirst(toRegex("K"), "-K");
        str = str.replaceFirst(toRegex("MD"), "-depth");

        return str;
    }

    public static String completeRandomT(String str) {
        str = str.replaceFirst(toRegex("RandomTree"), "weka.classifiers.trees.RandomTree");
        str = str.replaceFirst(toRegex("K"), "-K");
        str = str.replaceFirst(toRegex("M"), "-M");
        str = str.replaceFirst(toRegex("N"), "-N");
        str = str.replaceFirst(toRegex("MD"), "-depth");

        if(contains(str, "U TRUE"))
            str = str.replaceFirst(toRegex("U TRUE"), "-U");
        else if(contains(str, "U FALSE"))
            str = str.replaceFirst(toRegex("U FALSE"), "");

        return str;
    }

    public static String completeREPT(String str) {
        str = str.replaceFirst(toRegex("REPTree"), "weka.classifiers.trees.REPTree");
        str = str.replaceFirst(toRegex("L"), "-L");
        str = str.replaceFirst(toRegex("M"), "-M");
        str = str.replaceFirst(toRegex("V"), "-V");

        if(contains(str, "P TRUE"))
            str = str.replaceFirst(toRegex("P TRUE"), "-P");
        else if(contains(str, "P FALSE"))
            str = str.replaceFirst(toRegex("P FALSE"), "");

        return str;
    }
}
