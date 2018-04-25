package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class Logistic implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("Logistic"), "weka.classifiers.functions.Logistic");

        str = str.replaceFirst(toRegex("R"), "-R");


        return str;
    }
}
