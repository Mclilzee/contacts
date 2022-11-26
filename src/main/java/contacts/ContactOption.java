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
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "exit":
                    return;
                case "count":
                    printContactsCount();
                    break;
                case "info":
                    printContactInfo();
                    break;
                case "add":
                    addNewContact();
                    break;
                case "edit":
                    editContactsInstructions();
                    break;
                case "remove":
                    removeContact();
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
