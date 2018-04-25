package gremlin.grammar.meta;

import gremlin.grammar.MetaClassifier;

import static gremlin.grammar.Utils.toRegex;

public class LWL implements MetaClassifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("LWL"), "LWL");

        return str;
    }
}
