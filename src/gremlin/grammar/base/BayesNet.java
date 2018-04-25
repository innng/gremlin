package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.contains;
import static gremlin.grammar.Utils.toRegex;

public class BayesNet implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("BayesNet"), "weka.classifiers.bayes.BayesNet");

        if(contains(str, "D TRUE"))
            str = str.replaceFirst(toRegex("D TRUE"), "");
        else
            str = str.replaceFirst(toRegex("D FALSE"), "-D");

        str = str.replaceFirst(toRegex("Q"), "-Q");
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
        else
            str = str.replaceFirst(toRegex("TAN"), "weka.classifiers.bayes.net.search.local.TAN");

        return str;
    }
}
