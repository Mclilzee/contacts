package contacts;

import contacts.contact.Contact;
import contacts.phonebook.PhoneBook;
import contacts.utils.ContactPrinter;

import java.util.List;
import java.util.Scanner;

public class SearchEngine {

    private final PhoneBook phoneBook;
    private final Scanner scanner;


    public SearchEngine(PhoneBook phoneBook, Scanner scanner) {
        this.phoneBook = phoneBook;
        this.scanner = scanner;
    }

    private void printContactInfo() {
        List<Contact> contacts = phoneBook.getContacts();
        if (contacts.isEmpty()) {
            System.out.println("No records to show!");
            return;
        }

        int index = getContactIndex(contacts);
        ContactPrinter.printContactInfo(contacts, index);
    }

    private void editContactsInstructions() {
        List<Contact> contacts = phoneBook.getContacts();
        if (contacts.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        phoneBook.editContactInformation(getContactIndex(contacts), scanner);
        System.out.println("The record updated!");
    }

    private void removeContact() {
        List<Contact> contacts = phoneBook.getContacts();
        if (contacts.isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }

        phoneBook.removeContact(getContactIndex(contacts));
        System.out.println("The record removed!");
    }

    private int getContactIndex(List<Contact> contacts) {
        ContactPrinter.printIndexList(contacts);
        System.out.print("Select a record: ");
        return Integer.parseInt(scanner.nextLine()) - 1;
    }
}
