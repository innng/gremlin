package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class RandomTree implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("RandomTree"), "weka.classifiers.trees.RandomTree");

        return str;
    }
}
