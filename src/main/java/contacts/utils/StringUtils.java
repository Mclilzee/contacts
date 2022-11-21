package contacts.utils;

import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern pattern;

    static {
        String firstGroupWrapped = "(\\([\\da-z]+\\)[\\s-][\\da-z]{2,})";
        String secondGroupWrapped = "([\\da-z]+[\\s-]\\([\\da-z]{2,}\\))";
        String bothGroupsUnwrapped = "([\\d-z]+[\\s-]\\[\\da-z]{2,})";
        String fullRegex =  String.format("\\+?(%s|%s|%s)([\\s-][\\da-z])+", firstGroupWrapped, secondGroupWrapped, bothGroupsUnwrapped);
        pattern = Pattern.compile(fullRegex, Pattern.CASE_INSENSITIVE);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return pattern.matcher(phoneNumber).matches();
    }
}
