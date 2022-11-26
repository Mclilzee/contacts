package contacts;

import contacts.contact.Contact;

import java.util.List;
import java.util.Scanner;

public interface PhoneBook {

    void addContact(Contact contact);

    List<Contact> getContacts();

    List<String> getContactIndexInformation();

    String getContactInformation(int index);

    void editContactInformation(int i, Scanner scanner);

    void removeContact(int index);
}
