package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class RandomForest implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("RandomForest"), "weka.classifiers.trees.RandomForest");

        return str;
    }
}
