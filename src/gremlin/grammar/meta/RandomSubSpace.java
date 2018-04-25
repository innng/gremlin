package gremlin.grammar.meta;

import gremlin.grammar.MetaClassifier;

import static gremlin.grammar.Utils.toRegex;

public class RandomSubSpace implements MetaClassifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("RandomSubSpace"), "weka.classifiers.meta.RandomSubSpace");

        return str;
    }
}
