package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class NaiveBayesMultinomial implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("NaiveBayesMultinomial"), "weka.classifiers.bayes.NaiveBayesMultinomial");

        return str;
    }
}
