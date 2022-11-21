package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            return;
        }

        contacts.add(contact);
        System.out.println("The record added.");
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
