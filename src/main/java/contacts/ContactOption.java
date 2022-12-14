package contacts;

import contacts.contact.Contact;
import contacts.factory.OrganizationContactFactory;
import contacts.factory.PersonContactFactory;
import contacts.phonebook.PhoneBook;
import contacts.utils.ContactPrinter;

import java.util.List;
import java.util.Scanner;

public class ContactOption {

    private final PhoneBook phoneBook;
    private final Scanner scanner;

    public ContactOption(Scanner scanner, PhoneBook phoneBook) {
        this.scanner = scanner;
        this.phoneBook = phoneBook;
    }

    public void start() {
        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "exit":
                    return;
                case "search":
                    new SearchEngine(phoneBook, scanner);
                    break;
                case "count":
                    printContactsCount();
                    break;
                case "list":
                    listContactOptions();
                    break;
                case "add":
                    addNewContact();
                    break;
            }
            System.out.println();
        }
    }

    private void addNewContact() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine().toLowerCase();
        Contact contact;
        if ("person".equals(type)) {
            contact = new PersonContactFactory(scanner).createContact();
        } else {
            contact = new OrganizationContactFactory(scanner).createContact();
        }

        phoneBook.addContact(contact);
        System.out.println("The record added.");
    }

    private void printContactsCount() {
        System.out.println("The Phone Book has " + phoneBook.getContacts().size() + " records.");
    }

    private void listContactOptions() {
        List<Contact> contacts = phoneBook.getContacts();
        ContactPrinter.printIndexList(contacts);

        System.out.println("[list] Enter action ([number], back): ");
        String input = scanner.nextLine().toLowerCase();

        if (input.matches("\\d+")) {
            int index = Integer.parseInt(input) - 1;
            new ContactEditor(phoneBook, contacts.get(index), scanner);
        }
    }
}
