package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class SGD implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("SGD"), "weka.classifiers.functions.SGD");

        return str;
    }
}
