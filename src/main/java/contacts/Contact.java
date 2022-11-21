package contacts;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class Contact {

    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final Pattern phoneNumberPattern = createPhoneNumberPattern();

    private Pattern createPhoneNumberPattern() {
        String firstGroupWrapped = "(\\([\\da-z]+\\)[\\s-][\\da-z]{2,})";
        String secondGroupWrapped = "([\\da-z]+[\\s-]\\([\\da-z]{2,}\\))";
        String bothGroupsUnwrapped = "([\\d-z]+[\\s-]\\[\\da-z]{2,})";
        String fullRegex =  String.format("\\+?(%s|%s|%s)([\\s-][\\da-z])+", firstGroupWrapped, secondGroupWrapped, bothGroupsUnwrapped);
        return Pattern.compile(fullRegex, Pattern.CASE_INSENSITIVE);
    }

    public Contact(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;

        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
            System.out.println("Wrong phone number");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumberPattern.matcher(phoneNumber).matches();
    }
}
