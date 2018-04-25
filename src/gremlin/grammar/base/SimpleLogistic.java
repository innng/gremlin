package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.contains;
import static gremlin.grammar.Utils.toRegex;

public class SimpleLogistic implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("SimpleLogistic"), "weka.classifiers.functions.SimpleLogistic");

        if(contains(str, "A TRUE"))
            str = str.replaceFirst(toRegex("A TRUE"), "-A");
        else
            str = str.replaceFirst(toRegex("A FALSE"), "");

        if(contains(str, "S TRUE"))
            str = str.replaceFirst(toRegex("S TRUE"), "");
        else
            str = str.replaceFirst(toRegex("S FALSE"), "-S");

        str = str.replaceFirst(toRegex("W"), "-W");

        return str;
    }
}
