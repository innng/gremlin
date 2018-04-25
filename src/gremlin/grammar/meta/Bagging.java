package gremlin.grammar.meta;

import gremlin.grammar.MetaClassifier;

import static gremlin.grammar.Utils.toRegex;

public class Bagging implements MetaClassifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("Bagging"), "weka.classifiers.meta.Bagging");

        return str;
    }
}
