package contacts;

import contacts.contact.Contact;
import contacts.phonebook.PhoneBook;

import java.util.List;
import java.util.Scanner;

public class ContactEditor {
    private final PhoneBook phoneBook;
    private final Contact contact;
    private final Scanner scanner;

    public ContactEditor(PhoneBook phoneBook, Contact contact, Scanner scanner) {
        this.phoneBook = phoneBook;
        this.contact = contact;
        this.scanner = scanner;

    }

    private void editContactsInstructions() {
        phoneBook.editContactInformation(contact, scanner);
        System.out.println("The record updated!");
    }

    private void removeContact() {
        phoneBook.removeContact(contact);
        System.out.println("The record removed!");
    }
}
