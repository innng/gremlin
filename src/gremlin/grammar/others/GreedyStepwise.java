package gremlin.grammar.others;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class GreedyStepwise implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("GreedyStepwise"), "weka.attributeSelection.GreedyStepwise");

        return str;
    }
}
