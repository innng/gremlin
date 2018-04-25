package gremlin.grammar.ensemble;

import gremlin.grammar.EnsembleClassifier;

import static gremlin.grammar.Utils.toRegex;

public class Stacking implements EnsembleClassifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("Stacking"), "weka.classifiers.meta.Stacking");

        return str;
    }
}
