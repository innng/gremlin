package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class IBk implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("IBk"), "weka.classifiers.lazy.IBk");

        return str;
    }
}
