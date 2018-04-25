package gremlin.grammar.base;

import gremlin.grammar.Classifier;

import static gremlin.grammar.Utils.contains;
import static gremlin.grammar.Utils.toRegex;

public class NaiveBayes implements Classifier {
    @Override
    public String complete(String str) {
        str = str.replaceFirst(toRegex("NaiveBayes"), "weka.classifiers.bayes.NaiveBayes");

        if(contains(str, "K TRUE"))
            str = str.replaceFirst(toRegex("K TRUE"), "-K");
        else {
            str = str.replaceFirst(toRegex("K FALSE"), "");

            if(contains(str, "D TRUE"))
                str = str.replaceFirst(toRegex("D TRUE"), "-D");
            else
                str = str.replaceFirst(toRegex("D FALSE"), "");
        }

        return str;
    }
}
