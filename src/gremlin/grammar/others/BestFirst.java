package gremlin.grammar.others;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.toRegex;

public class BestFirst implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("BestFirst"), "weka.attributeSelection.BestFirst");

        return str;
    }
}
