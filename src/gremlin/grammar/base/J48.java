package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class J48 implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("J48"), "weka.classifiers.trees.J48");

        return str;
    }
}
