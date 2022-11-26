package contacts;

import contacts.contact.Contact;
import contacts.factory.OrganizationContactFactory;
import contacts.factory.PersonContactFactory;
import contacts.phonebook.PhoneBook;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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

    private void printContactIndexList() {
        List<Contact> contacts = phoneBook.getContacts();

    }

    private void printContactInfo() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to show!");
            return;
        }

        int index = getContactIndex();
        String info = phoneBook.getContacts().get(index).getInfo();
        System.out.println(info);
    }

    private void editContactsInstructions() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        phoneBook.editContactInformation(getContactIndex(), scanner);
        System.out.println("The record updated!");
    }

    private void removeContact() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }

        phoneBook.removeContact(getContactIndex());
        System.out.println("The record removed!");
    }

    private int getContactIndex() {
        printContactIndexList();
        System.out.print("Select a record: ");
        return Integer.parseInt(scanner.nextLine()) - 1;
    }
}
