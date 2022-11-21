package contacts;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class Contact {

    private static final Pattern pattern;

    static {
        String firstGroupWrapped = "(\\([\\da-z]+\\)[\\s-][\\da-z]{2,})";
        String secondGroupWrapped = "([\\da-z]+[\\s-]\\([\\da-z]{2,}\\))";
        String bothGroupsUnwrapped = "([\\d-z]+[\\s-]\\[\\da-z]{2,})";
        String fullRegex =  String.format("\\+?(%s|%s|%s)([\\s-][\\da-z])+", firstGroupWrapped, secondGroupWrapped, bothGroupsUnwrapped);
        pattern = Pattern.compile(fullRegex, Pattern.CASE_INSENSITIVE);
    }

    private final String name;
    private final String surname;
    private String phoneNumber;



    public Contact(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;

        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong phone number!");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong phone number!");
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return pattern.matcher(phoneNumber).matches();
    }
}
