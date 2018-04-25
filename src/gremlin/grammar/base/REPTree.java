package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class REPTree implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("REPTree"), "weka.classifiers.trees.REPTree");

        return str;
    }
}
