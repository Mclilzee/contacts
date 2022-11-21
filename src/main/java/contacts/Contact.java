package contacts;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

@Getter @Setter
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
            System.out.println("Wrong number format!");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        return pattern.matcher(phoneNumber).matches();
    }
}
