package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class OneR implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("OneR"), "weka.classifiers.rules.OneR");

        return str;
    }
}
