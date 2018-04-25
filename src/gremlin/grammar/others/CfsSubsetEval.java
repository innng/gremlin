package gremlin.grammar.others;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class CfsSubsetEval implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("CfsSubsetEval"), "weka.attributeSelection.CfsSubsetEval");

        return str;
    }
}
