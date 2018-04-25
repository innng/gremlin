package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class DecisionTable implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("DecisionTable"), "weka.classifiers.rules.DecisionTable");

        return str;
    }
}
