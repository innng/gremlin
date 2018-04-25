package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class SMO implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("SMO"), "weka.classifiers.functions.SMO");

        return str;
    }
}
