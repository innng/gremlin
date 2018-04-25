package gremlin.grammar.meta;

import gremlin.grammar.MetaClassifier;

import static gremlin.grammar.Utils.toRegex;

public class AttributeSelectedClassifier implements MetaClassifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("AttributeSelectedClassifier"), "weka.classifiers.meta.AttributeSelectedClassifier");

        return str;
    }
}
