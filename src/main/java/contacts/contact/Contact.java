package contacts.contact;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime createdDateTime;
    private LocalDateTime lastEditedDateTime;

    public Contact(String phoneNumber) {
        LocalDateTime current = LocalDateTime.now().withNano(0);
        createdDateTime = current;
        lastEditedDateTime = current;

        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
    }

    protected void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Wrong number format!");
        }
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        return pattern.matcher(phoneNumber).matches();
    }

    public void editContact(Scanner scanner) {
        lastEditedDateTime = LocalDateTime.now().withNano(0);
        editInformation(scanner);
    }

    public abstract String getInfo();

    protected abstract void editInformation(Scanner scanner);

    public abstract String getName();
}
