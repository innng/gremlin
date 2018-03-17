package gremlin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static gremlin.Meta.addSeparatorByQMarks;
import static gremlin.Utils.toRegex;

public class Ensemble {

    public static String completeStacking(String str) {
//        TODO
//         -X 10 -M "weka.classifiers.rules.ZeroR " -S 1 -num-slots 1 -B "weka.classifiers.rules.ZeroR "
        int noIt = noIterations(str);
        str = str.replaceFirst(toRegex("Stacking"), "weka.classifiers.meta.Stacking");

//        for(int i = 0; i < noIt; i++) {
//            str = str.replaceFirst(toRegex("B"), "-B");
//            str = addSeparatorByQMarks(str);
//            str = Utils.completeBaseClassifier(str);
//        }

        return str;
    }

    public static String completeVote(String str) {
//        TODO
        int noIt = noIterations(str);
        str = str.replaceFirst(toRegex("Vote"), "weka.classifiers.meta.Vote");
        str = str.replaceFirst(toRegex("R"), "-R");

//        for(int i = 0; i < noIt; i++) {
//            str = str.replaceFirst(toRegex("B"), "-B");
//            str = addSeparatorByQMarks(str);
//            str = Utils.completeBaseClassifier(str);
//        }

        return str;
    }

    public static int noIterations(String source) {
        int iterations = 0;
        String auxiliar = "";

        String string = "\\b[1-5]{1}C\\b";
        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher(source);

        if(matcher.find())
            auxiliar = matcher.group();

        auxiliar = auxiliar.substring(0, 1);
        iterations = Integer.parseInt(auxiliar);

        return iterations;
    }
}
