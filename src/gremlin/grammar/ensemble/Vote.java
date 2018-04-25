package gremlin.grammar.ensemble;

import gremlin.grammar.EnsembleClassifier;

import static gremlin.grammar.Utils.toRegex;

public class Vote implements EnsembleClassifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("Vote"), "weka.classifiers.meta.Vote");

        return str;
    }
}
