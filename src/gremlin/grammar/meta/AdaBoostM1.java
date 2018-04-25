package gremlin.grammar.meta;

import gremlin.grammar.MetaClassifier;

import static gremlin.grammar.Utils.toRegex;

public class AdaBoostM1 implements MetaClassifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("AdaBoostM1"), "weka.classifiers.meta.AdaBoostM1");

        return str;
    }
}
