package contacts.phonebook;

import contacts.contact.Contact;

import java.util.*;
import java.util.stream.IntStream;

public class MemoryPhoneBook implements PhoneBook {

    private List<Contact> contacts = new ArrayList<>();

    @Override
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException();
        }

        contacts.add(contact);
    }

    @Override
    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    @Override
    public void editContactInformation(Contact contact, Scanner scanner) {
        contact.editContact(scanner);
    }

    @Override
    public void removeContact(Contact contact) {
        contacts = contacts.stream()
                .filter(element -> !Objects.equals(contact, element))
                .toList();
    }
}
