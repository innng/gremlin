package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class JRip implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("JRip"), "weka.classifiers.rules.JRip");

        return str;
    }
}
