package gremlin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String completeConfiguration(String config) {
        if(contains(config, "m"))
        return config;
    }

    public static boolean contains(String source, String substring) {
        String patternToMatch = "\\b" + substring + "\\b";
        Pattern pattern = Pattern.compile(patternToMatch);
        Matcher matcher = pattern.matcher(source);
        return matcher.find();
    }

    public static String toRegex(String source) {
        return "\\b" + source + "\\b";
    }

    public static String addSeparator(String source) {
        String separator = "--";

        return source;
    }
}
