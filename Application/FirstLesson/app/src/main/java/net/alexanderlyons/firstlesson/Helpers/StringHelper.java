package net.alexanderlyons.firstlesson.Helpers;

/**
 * Created by PyroticBlaziken on 10/22/2015.
 * Made using: http://stackoverflow.com/questions/8476588/java-equivalent-of-c-sharp-string-isnullorempty-and-string-isnullorwhitespace
 */
public class StringHelper {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrWhitespace(String s) {
        return s == null || isWhitespace(s);

    }
    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
