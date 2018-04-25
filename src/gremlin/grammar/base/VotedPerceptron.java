package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class VotedPerceptron implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("VotedPerceptron"), "weka.classifiers.functions.VotedPerceptron");

        return str;
    }
}
