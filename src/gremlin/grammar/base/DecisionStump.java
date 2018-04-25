package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class DecisionStump implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("DecisionStump"), "weka.classifiers.trees.DecisionStump");

        return str;
    }
}
