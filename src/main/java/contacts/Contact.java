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
        String bothGroupsUnwrapped = "([\\da-z]+[\\s-][\\da-z]{2,})";
        String fullRegex =  String.format("\\+?(\\(?[\\da-z]+\\)?|(%s|%s|%s)([\\s-][\\da-z]{2,})*)", firstGroupWrapped, secondGroupWrapped, bothGroupsUnwrapped);
        pattern = Pattern.compile(fullRegex, Pattern.CASE_INSENSITIVE);
    }

    private String name;
    private String surname;
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

    private static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        return pattern.matcher(phoneNumber).matches();
    }

    @Override
    public String toString() {
        return name + " " + surname + ", " + phoneNumber;
    }

}
