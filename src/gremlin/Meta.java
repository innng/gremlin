package gremlin;

public class Meta {

    public static String AttributeSelectedClassifier(String str) {
        str = str.replaceFirst(Utils.toRegex("AttributeSelectedClassifier"), "weka.classifiers.meta.AttributeSelectedClassifier");
        return str;
    }

    public static String LWL(String str) {
        str = str.replaceFirst(Utils.toRegex("LWL"), "weka.classifiers.lazy.LWL");
        return str;
    }

    public static String AdaBoostM1(String str) {
        str = str.replaceFirst(Utils.toRegex("AdaBoostM1"), "weka.classifiers.meta.AdaBoostM1");
        return str;
    }

    public static String Bagging(String str) {
        str = str.replaceFirst(Utils.toRegex("Bagging"), "weka.classifiers.meta.Bagging");
        return str;
    }

    public static String RandomCommittee(String str) {
        str = str.replaceFirst(Utils.toRegex("RandomCommittee"), "weka.classifiers.meta.RandomCommittee");
        return str;
    }

    public static String RandomSubSpace(String str) {
        str = str.replaceFirst(Utils.toRegex("RandomSubSpace"), "weka.classifiers.meta.RandomSubSpace");
        return str;
    }

    /*
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
    }*/
}
