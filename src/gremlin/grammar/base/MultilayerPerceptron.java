package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.contains;
import static gremlin.grammar.Utils.toRegex;

public class MultilayerPerceptron implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("MultilayerPerceptron"), "weka.classifiers.functions.MultilayerPerceptron");

        if(contains(str, "B TRUE"))
            str = str.replaceFirst(toRegex("B TRUE"), "");
        else
            str = str.replaceFirst(toRegex("B FALSE"), "-B");

        if(contains(str, "C TRUE"))
            str = str.replaceFirst(toRegex("C TRUE"), "");
        else
            str = str.replaceFirst(toRegex("C FALSE"), "-C");

        if(contains(str, "D TRUE"))
            str = str.replaceFirst(toRegex("D TRUE"), "-D");
        else
            str = str.replaceFirst(toRegex("D FALSE"), "");

        str = str.replaceFirst(toRegex("H"), "-H");

        str = str.replaceFirst(toRegex("L"), "-L");

        str = str.replaceFirst(toRegex("M"), "-M");

        if(contains(str, "R TRUE"))
            str = str.replaceFirst(toRegex("R TRUE"), "");
        else
            str = str.replaceFirst(toRegex("R FALSE"), "-R");

        return str;
    }
}
