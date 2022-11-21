package contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneBook {

    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException();
        }

        contacts.add(contact);
    }

    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    public List<String> getRecordsInformation() {
        return List.of();
    }
}
