package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class KStar implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("KStar"), "weka.classifiers.lazy.KStar");

        return str;
    }
}
