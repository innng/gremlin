package gremlin;

public class Base {

    public static String BayesNet(String str) {
        str = str.replaceFirst(Utils.toRegex("BayesNet"), "weka.classifiers.bayes.BayesNet");
        str = str.replaceFirst(Utils.toRegex("Q"), "-Q");

        if(Utils.contains(str, "D TRUE"))
            str = str.replaceFirst(Utils.toRegex("D TRUE"), "");
        if(Utils.contains(str, "D FALSE"))
            str = str.replaceFirst(Utils.toRegex("D FALSE"), "-D");

        if(Utils.contains(str, "K2"))
            str = str.replaceFirst(Utils.toRegex("K2"), "weka.classifiers.bayes.net.search.local.K2");
        else if(Utils.contains(str, "HillClimber"))
            str = str.replaceFirst(Utils.toRegex("HillClimber"), "weka.classifiers.bayes.net.search.local.HillClimber");
        else if(Utils.contains(str, "LAGDHillClimber"))
            str = str.replaceFirst(Utils.toRegex("LAGDHillClimber"), "weka.classifiers.bayes.net.search.local.LAGDHillClimber");
        else if(Utils.contains(str, "SimulatedAnnealing"))
            str = str.replaceFirst(Utils.toRegex("SimulatedAnnealing"), "weka.classifiers.bayes.net.search.local.SimulatedAnnealing");
        else if(Utils.contains(str, "TabuSearch"))
            str = str.replaceFirst(Utils.toRegex("TabuSearch"), "weka.classifiers.bayes.net.search.local.TabuSearch");
        else if(Utils.contains(str, "TAN"))
            str = str.replaceFirst(Utils.toRegex("TAN"), "weka.classifiers.bayes.net.search.local.TAN");

        return str;
    }

    public static String NaiveBayes(String str) {
        str = str.replaceFirst(Utils.toRegex("NaiveBayes"), "weka.classifiers.bayes.NaiveBayes");

        if(Utils.contains(str, "K TRUE"))
            str = str.replaceFirst(Utils.toRegex("K TRUE"), "-K");
        if(Utils.contains(str, "K FALSE"))
            str = str.replaceFirst(Utils.toRegex("K FALSE"), "");

        if(Utils.contains(str, "D TRUE"))
            str = str.replaceFirst(Utils.toRegex("D TRUE"), "-D");
        if(Utils.contains(str, "D FALSE"))
            str = str.replaceFirst(Utils.toRegex("D FALSE"), "");

        return str;
    }

    public static String NaiveBayesMultinomial(String str) {
        str = str.replaceFirst(Utils.toRegex("NaiveBayesMultinomial"), "weka.classifiers.bayes.NaiveBayesMultinomial");

        return str;
    }

    public static String Logistic(String str) {
        str = str.replaceFirst(Utils.toRegex("Logistic"), "weka.classifiers.functions.Logistic");
        str = str.replaceFirst(Utils.toRegex("R"), "-R");

        return str;
    }

    public static String MultilayerPerceptron(String str) {
        str = str.replaceFirst(Utils.toRegex("MultilayerPerceptron"), "weka.classifiers.functions.MultilayerPerceptron");
        str = str.replaceFirst(Utils.toRegex("H"), "-H");
        str = str.replaceFirst(Utils.toRegex("L"), "-L");
        str = str.replaceFirst(Utils.toRegex("M"), "-M");

        if(Utils.contains(str, "B TRUE"))
            str = str.replaceFirst(Utils.toRegex("B TRUE"), "");
        if(Utils.contains(str, "B FALSE"))
            str = str.replaceFirst(Utils.toRegex("B FALSE"), "-B");

        if(Utils.contains(str, "C TRUE"))
            str = str.replaceFirst(Utils.toRegex("C TRUE"), "");
        if(Utils.contains(str, "C FALSE"))
            str = str.replaceFirst(Utils.toRegex("C FALSE"), "-C");

        if(Utils.contains(str, "D TRUE"))
            str = str.replaceFirst(Utils.toRegex("D TRUE"), "-D");
        if(Utils.contains(str, "D FALSE"))
            str = str.replaceFirst(Utils.toRegex("D FALSE"), "");

        if(Utils.contains(str, "R TRUE"))
            str = str.replaceFirst(Utils.toRegex("R TRUE"), "");
        if(Utils.contains(str, "R FALSE"))
            str = str.replaceFirst(Utils.toRegex("R FALSE"), "-R");

        return str;
    }

    public static String SGD(String str) {
        str = str.replaceFirst(Utils.toRegex("SGD"), "weka.classifiers.functions.SGD");
        str = str.replaceFirst(Utils.toRegex("F"), "-F");
        str = str.replaceFirst(Utils.toRegex("L"), "-L");
        str = str.replaceFirst(Utils.toRegex("R"), "-R");

        if(Utils.contains(str, "M TRUE"))
            str = str.replaceFirst(Utils.toRegex("M TRUE"), "-M");
        if(Utils.contains(str, "M FALSE"))
            str = str.replaceFirst(Utils.toRegex("M FALSE"), "");

        if(Utils.contains(str, "N TRUE"))
            str = str.replaceFirst(Utils.toRegex("N TRUE"), "-N");
        if(Utils.contains(str, "N FALSE"))
            str = str.replaceFirst(Utils.toRegex("N FALSE"), "");

        return str;
    }

    public static String SimpleLogistic(String str) {
        str = str.replaceFirst(Utils.toRegex("SimpleLogistic"), "weka.classifiers.functions.SimpleLogistic");
        str = str.replaceFirst(Utils.toRegex("W"), "-W");


        if(Utils.contains(str, "A TRUE"))
            str = str.replaceFirst(Utils.toRegex("A TRUE"), "-A");
        if(Utils.contains(str, "A FALSE"))
            str = str.replaceFirst(Utils.toRegex("A FALSE"), "");

        if(Utils.contains(str, "S TRUE"))
            str = str.replaceFirst(Utils.toRegex("S TRUE"), "");
        if(Utils.contains(str, "S FALSE"))
            str = str.replaceFirst(Utils.toRegex("S FALSE"), "-S");

        return str;
    }

    public static String SMO(String str) {
        str = str.replaceFirst(Utils.toRegex("SMO"), "weka.classifiers.functions.SMO");
        str = str.replaceFirst(Utils.toRegex("C"), "-C");
        str = str.replaceFirst(Utils.toRegex("N"), "-N");

        if(Utils.contains(str, "M TRUE"))
            str = str.replaceFirst(Utils.toRegex("M TRUE"), "-M");
        if(Utils.contains(str, "M FALSE"))
            str = str.replaceFirst(Utils.toRegex("M FALSE"), "");

        return str;
    }

    public static String VotedPerceptron(String str) {
        str = str.replaceFirst(Utils.toRegex("VotedPerceptron"), "weka.classifiers.functions.VotedPerceptron");
        str = str.replaceFirst(Utils.toRegex("E"), "-E");
        str = str.replaceFirst(Utils.toRegex("I"), "-I");
        str = str.replaceFirst(Utils.toRegex("M"), "-M");

        return str;
    }

    public static String IBk(String str) {
        str = str.replaceFirst(Utils.toRegex("IBk"), "weka.classifiers.lazy.IBk");
        str = str.replaceFirst(Utils.toRegex("K"), "-K");

        if(Utils.contains(str, "E TRUE"))
            str = str.replaceFirst(Utils.toRegex("E TRUE"), "-E");
        if(Utils.contains(str, "E FALSE"))
            str = str.replaceFirst(Utils.toRegex("E FALSE"), "");

        if(Utils.contains(str, "X TRUE"))
            str = str.replaceFirst(Utils.toRegex("X TRUE"), "-X");
        if(Utils.contains(str, "X FALSE"))
            str = str.replaceFirst(Utils.toRegex("X FALSE"), "");

        if(Utils.contains(str, "IF I"))
            str = str.replaceFirst(Utils.toRegex("IF I"), "-I");
        else if(Utils.contains(str, "IF F"))
            str = str.replaceFirst(Utils.toRegex("IF F"), "-F");
        else if(Utils.contains(str, "IF FALSE"))
            str = str.replaceFirst(Utils.toRegex("IF FALSE"), "");

        return str;
    }

    public static String KStar(String str) {
        str = str.replaceFirst(Utils.toRegex("KStar"), "weka.classifiers.lazy.KStar");
        str = str.replaceFirst(Utils.toRegex("B"), "-B");
        str = str.replaceFirst(Utils.toRegex("M"), "-M");

        if(Utils.contains(str, "E TRUE"))
            str = str.replaceFirst(Utils.toRegex("E TRUE"), "-E");
        if(Utils.contains(str, "E FALSE"))
            str = str.replaceFirst(Utils.toRegex("E FALSE"), "");

        return str;
    }

    public static String DecisionTable(String str) {
        str = str.replaceFirst(Utils.toRegex("DecisionTable"), "weka.classifiers.rules.DecisionTable");
        str = str.replaceFirst(Utils.toRegex("E"), "-E");
        str = str.replaceFirst(Utils.toRegex("X"), "-X");

        if(Utils.contains(str, "I TRUE"))
            str = str.replaceFirst(Utils.toRegex("I TRUE"), "-I");
        if(Utils.contains(str, "I FALSE"))
            str = str.replaceFirst(Utils.toRegex("I FALSE"), "");

        return str;
    }

    public static String JRip(String str) {
        str = str.replaceFirst(Utils.toRegex("JRip"), "weka.classifiers.rules.JRip");
        str = str.replaceFirst(Utils.toRegex("N"), "-N");
        str = str.replaceFirst(Utils.toRegex("O"), "-O");

        if(Utils.contains(str, "E TRUE"))
            str = str.replaceFirst(Utils.toRegex("E TRUE"), "");
        if(Utils.contains(str, "E FALSE"))
            str = str.replaceFirst(Utils.toRegex("E FALSE"), "-E");

        if(Utils.contains(str, "P TRUE"))
            str = str.replaceFirst(Utils.toRegex("P TRUE"), "");
        if(Utils.contains(str, "P FALSE"))
            str = str.replaceFirst(Utils.toRegex("P FALSE"), "-P");

        return str;
    }

    public static String OneR(String str) {
        str = str.replaceFirst(Utils.toRegex("OneR"), "weka.classifiers.rules.OneR");
        str = str.replaceFirst(Utils.toRegex("B"), "-B");

        return str;
    }

    public static String PART(String str) {
        str = str.replaceFirst(Utils.toRegex("PART"), "weka.classifiers.rules.PART");
        str = str.replaceFirst(Utils.toRegex("M"), "-M");
        str = str.replaceFirst(Utils.toRegex("N"), "-N");

        if(Utils.contains(str, "B TRUE"))
            str = str.replaceFirst(Utils.toRegex("B TRUE"), "-B");
        if(Utils.contains(str, "B FALSE"))
            str = str.replaceFirst(Utils.toRegex("B FALSE"), "");

        if(Utils.contains(str, "R TRUE"))
            str = str.replaceFirst(Utils.toRegex("R TRUE"), "-R");
        if(Utils.contains(str, "R FALSE"))
            str = str.replaceFirst(Utils.toRegex("R FALSE"), "");

        return str;
    }

    public static String ZeroR(String str) {
        str = str.replaceFirst(Utils.toRegex("ZeroR"), "weka.classifiers.rules.ZeroR");

        return str;
    }

    public static String DecisionStump(String str) {
        str = str.replaceFirst(Utils.toRegex("DecisionStump"), "weka.classifiers.trees.DecisionStump");

        return str;
    }

    public static String J48(String str) {
        str = str.replaceFirst(Utils.toRegex("J48"), "weka.classifiers.trees.J48");
        str = str.replaceFirst(Utils.toRegex("C"), "-C");
        str = str.replaceFirst(Utils.toRegex("M"), "-M");


        if(Utils.contains(str, "A TRUE"))
            str = str.replaceFirst(Utils.toRegex("A TRUE"), "-A");
        if(Utils.contains(str, "A FALSE"))
            str = str.replaceFirst(Utils.toRegex("A FALSE"), "");

        if(Utils.contains(str, "B TRUE"))
            str = str.replaceFirst(Utils.toRegex("B TRUE"), "-B");
        if(Utils.contains(str, "B FALSE"))
            str = str.replaceFirst(Utils.toRegex("B FALSE"), "");

        if(Utils.contains(str, "J TRUE"))
            str = str.replaceFirst(Utils.toRegex("J TRUE"), "");
        if(Utils.contains(str, "J FALSE"))
            str = str.replaceFirst(Utils.toRegex("J FALSE"), "-J");

        if(Utils.contains(str, "O TRUE"))
            str = str.replaceFirst(Utils.toRegex("O TRUE"), "");
        if(Utils.contains(str, "O FALSE"))
            str = str.replaceFirst(Utils.toRegex("O FALSE"), "-O");

        if(Utils.contains(str, "S TRUE"))
            str = str.replaceFirst(Utils.toRegex("S TRUE"), "");
        if(Utils.contains(str, "S FALSE"))
            str = str.replaceFirst(Utils.toRegex("S FALSE"), "-S");

        if(Utils.contains(str, "U TRUE"))
            str = str.replaceFirst(Utils.toRegex("U TRUE"), "-U");
        if(Utils.contains(str, "U FALSE"))
            str = str.replaceFirst(Utils.toRegex("U FALSE"), "");

        return str;
    }

    public static String LMT(String str) {
        str = str.replaceFirst(Utils.toRegex("LMT"), "weka.classifiers.trees.LMT");
        str = str.replaceFirst(Utils.toRegex("M"), "-M");
        str = str.replaceFirst(Utils.toRegex("W"), "-W");


        if(Utils.contains(str, "A TRUE"))
            str = str.replaceFirst(Utils.toRegex("A TRUE"), "-A");
        if(Utils.contains(str, "A FALSE"))
            str = str.replaceFirst(Utils.toRegex("A FALSE"), "");

        if(Utils.contains(str, "B TRUE"))
            str = str.replaceFirst(Utils.toRegex("B TRUE"), "-B");
        if(Utils.contains(str, "B FALSE"))
            str = str.replaceFirst(Utils.toRegex("B FALSE"), "");

        if(Utils.contains(str, "C TRUE"))
            str = str.replaceFirst(Utils.toRegex("C TRUE"), "");
        if(Utils.contains(str, "C FALSE"))
            str = str.replaceFirst(Utils.toRegex("C FALSE"), "-C");

        if(Utils.contains(str, "P TRUE"))
            str = str.replaceFirst(Utils.toRegex("P TRUE"), "-P");
        if(Utils.contains(str, "P FALSE"))
            str = str.replaceFirst(Utils.toRegex("P FALSE"), "");

        if(Utils.contains(str, "R TRUE"))
            str = str.replaceFirst(Utils.toRegex("R TRUE"), "-R");
        if(Utils.contains(str, "R FALSE"))
            str = str.replaceFirst(Utils.toRegex("R FALSE"), "");


        return str;
    }

    public static String RandomForest(String str) {
        str = str.replaceFirst(Utils.toRegex("RandomForest"), "weka.classifiers.trees.RandomForest");
        str = str.replaceFirst(Utils.toRegex("I"), "-I");
        str = str.replaceFirst(Utils.toRegex("K"), "-K");
        str = str.replaceFirst(Utils.toRegex("MD"), "-depth");

        return str;
    }

    public static String RandomTree(String str) {
        str = str.replaceFirst(Utils.toRegex("RandomTree"), "weka.classifiers.trees.RandomTree");
        str = str.replaceFirst(Utils.toRegex("K"), "-K");
        str = str.replaceFirst(Utils.toRegex("M"), "-M");
        str = str.replaceFirst(Utils.toRegex("N"), "-N");
        str = str.replaceFirst(Utils.toRegex("MD"), "-depth");

        if(Utils.contains(str, "U TRUE"))
            str = str.replaceFirst(Utils.toRegex("U TRUE"), "-U");
        if(Utils.contains(str, "U FALSE"))
            str = str.replaceFirst(Utils.toRegex("U FALSE"), "");

        return str;
    }

    public static String REPTree(String str) {
        str = str.replaceFirst(Utils.toRegex("REPTree"), "weka.classifiers.trees.REPTree");
        str = str.replaceFirst(Utils.toRegex("L"), "-L");
        str = str.replaceFirst(Utils.toRegex("M"), "-M");
        str = str.replaceFirst(Utils.toRegex("V"), "-V");

        if(Utils.contains(str, "P TRUE"))
            str = str.replaceFirst(Utils.toRegex("P TRUE"), "-P");
        if(Utils.contains(str, "P FALSE"))
            str = str.replaceFirst(Utils.toRegex("P FALSE"), "");

        return str;
    }

}
