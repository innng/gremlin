package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class PART implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("PART"), "weka.classifiers.rules.PART");

        return str;
    }
}
