package contacts;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;
import java.util.regex.Pattern;

@Getter @Setter
public abstract class Contact {

    private static final Pattern pattern;

    static {
        String firstGroupWrapped = "(\\([\\da-z]+\\)[\\s-][\\da-z]{2,})";
        String secondGroupWrapped = "([\\da-z]+[\\s-]\\([\\da-z]{2,}\\))";
        String bothGroupsUnwrapped = "([\\da-z]+[\\s-][\\da-z]{2,})";
        String fullRegex =  String.format("\\+?(\\(?[\\da-z]+\\)?|(%s|%s|%s)([\\s-][\\da-z]{2,})*)", firstGroupWrapped, secondGroupWrapped, bothGroupsUnwrapped);
        pattern = Pattern.compile(fullRegex, Pattern.CASE_INSENSITIVE);
    }
    private String phoneNumber;

    public Contact(String phoneNumber) {
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

    private static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        return pattern.matcher(phoneNumber).matches();
    }

    public abstract String getInfo();

    public abstract void editInformation(Scanner scanner);
}
