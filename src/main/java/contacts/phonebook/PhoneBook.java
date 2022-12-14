package contacts.phonebook;

import contacts.contact.Contact;

import java.util.List;
import java.util.Scanner;

public interface PhoneBook {

    void addContact(Contact contact);

    List<Contact> getContacts();

    void editContactInformation(Contact contact, Scanner scanner);

    void removeContact(Contact contact);
}
