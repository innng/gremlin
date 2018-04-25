package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class ZeroR implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("ZeroR"), "weka.classifiers.rules.ZeroR");

        return str;
    }
}
