package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
