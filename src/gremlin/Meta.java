package gremlin;

import static gremlin.Utils.completeBaseClassifier;
import static gremlin.Utils.contains;
import static gremlin.Utils.toRegex;

public class Meta {

    public static String completeAttributeSC(String str) {
//        TODO
//         -E "weka.attributeSelection.CfsSubsetEval -P 1 -E 1" -S "weka.attributeSelection.BestFirst -D 1 -N 5" -W weka.classifiers.trees.J48 -- -C 0.25 -M 2
        str = str.replaceFirst(toRegex("AttributeSelectedClassifier"), "weka.classifiers.meta.AttributeSelectedClassifier");
        str = str.replaceFirst(toRegex("W"), "-W");

//        str = str.replaceFirst(toRegex("E"), "-E");
//        str = addSeparatorByQMarks(str);
//        str = str.replaceFirst(toRegex("S"), "-S");
//        str = addSeparatorByQMarks(str);

        str = addSeparatorByHyphen(str);
        str = completeBaseClassifier(str);

        return str;
    }

    public static String completeLWL(String str) {
        str = str.replaceFirst(toRegex("LWL"), "weka.classifiers.lazy.LWL");
        str = str.replaceFirst(toRegex("K"), "-K");
        str = str.replaceFirst(toRegex("U"), "-U");
        str = str.replaceFirst(toRegex("W"), "-W");

        str = addSeparatorByHyphen(str);
        str = completeBaseClassifier(str);

        return str;
    }

    public static String completeAdaBM1(String str) {
        str = str.replaceFirst(toRegex("AdaBoostM1"), "weka.classifiers.meta.AdaBoostM1");
        str = str.replaceFirst(toRegex("I"), "-I");
        str = str.replaceFirst(toRegex("P"), "-P");
        str = str.replaceFirst(toRegex("W"), "-W");

        if(contains(str, "Q TRUE"))
            str = str.replaceFirst(toRegex("Q TRUE"), "-Q");
        else if(contains(str, "Q FALSE"))
            str = str.replaceFirst(toRegex("Q FALSE"), "");

        str = addSeparatorByHyphen(str);
        str = completeBaseClassifier(str);

        return str;
    }

    public static String completeBagging(String str) {
        str = str.replaceFirst(toRegex("Bagging"), "weka.classifiers.meta.Bagging");
        str = str.replaceFirst(toRegex("I"), "-I");
        str = str.replaceFirst(toRegex("P"), "-P");
        str = str.replaceFirst(toRegex("W"), "-W");

        if(contains(str, "O TRUE"))
            str = str.replaceFirst(toRegex("O TRUE"), "-O");
        else if(contains(str, "O FALSE"))
            str = str.replaceFirst(toRegex("O FALSE"), "");

        str = addSeparatorByHyphen(str);
        str = completeBaseClassifier(str);

        return str;
    }

    public static String completeRandomCom(String str) {
        str = str.replaceFirst(toRegex("RandomCommittee"), "weka.classifiers.meta.RandomCommittee");
        str = str.replaceFirst(toRegex("I"), "-I");
        str = str.replaceFirst(toRegex("W"), "-W");

        str = addSeparatorByHyphen(str);
        str = completeBaseClassifier(str);

        return str;
    }

    public static String completeRandomSS(String str) {
        str = str.replaceFirst(toRegex("RandomSubSpace"), "weka.classifiers.meta.RandomSubSpace");
        str = str.replaceFirst(toRegex("I"), "-I");
        str = str.replaceFirst(toRegex("P"), "-P");
        str = str.replaceFirst(toRegex("W"), "-W");

        str = addSeparatorByHyphen(str);
        str = completeBaseClassifier(str);

        return str;
    }

    public static String addSeparatorByQMarks(String source, int indexStart, int indexEnd) {
        String separator = "\"";
        String[] auxiliar = source.split("\\s+");
        String string = "";

        for(int i = 0; i < indexStart; i++)
            string += auxiliar[i] + " ";

        string += separator;

        for(int i = indexStart; i < indexEnd; i++)
            string += auxiliar[i] + " ";

        string += separator;

        for(int i = indexEnd; i < auxiliar.length; i++)
            string += auxiliar[i] + " ";

        return string;
    }

    public static String addSeparatorByHyphen(String source) {
        String separator = "--";
        String[] auxiliar = source.split("\\s+");
        String string = "";
        int index = 0;

        for(int i = 0; i < auxiliar.length; i++)
            if(auxiliar[i].equals("-W")){
                index = i + 2;
                break;
            }

        for(int i = 0; i < index; i++)
            string += auxiliar[i] + " ";

        string += separator + " ";

        for(int i = index; i < auxiliar.length; i++)
            string += auxiliar[i] + " ";

        return string;
    }
}
