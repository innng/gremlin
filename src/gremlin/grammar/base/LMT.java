package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class LMT implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("LMT"), "weka.classifiers.trees.LMT");

        return str;
    }
}
