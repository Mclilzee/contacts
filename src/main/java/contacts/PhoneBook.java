package contacts;

import contacts.contact.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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

    public List<String> getContactIndexInformation() {
        return IntStream.range(0, contacts.size())
                .mapToObj(index -> (index + 1) + ". " + contacts.get(index).getName())
                .toList();
    }

    public String getContactInformation(int index) {
        return contacts.get(index).getInfo();
    }

    public void editContactInformation(int i, Scanner scanner) {
        contacts.get(i).editContact(scanner);
    }

    public void removeContact(int index) {
        contacts.remove(index);
    }
}
