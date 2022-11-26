package contacts.contact;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {
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
    private final UUID id;

    public Contact(String phoneNumber) {
        LocalDateTime current = LocalDateTime.now().withNano(0);
        createdDateTime = current;
        lastEditedDateTime = current;
        id = UUID.randomUUID();

        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public LocalDateTime getLastEditedDateTime() {
        return lastEditedDateTime;
    }

    public UUID getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public abstract String getInfo();

    protected abstract void editInformation(Scanner scanner);

    public abstract String getFullName();
}
