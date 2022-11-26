package contacts;

import contacts.contact.Contact;
import contacts.factory.ContactFactory;
import contacts.factory.OrganizationContactFactory;
import contacts.factory.PersonContactFactory;

import java.util.Scanner;

public class ContactOption {

    private PhoneBook memoryPhoneBook = new MemoryPhoneBook();
    private final Scanner scanner;

    public ContactOption(Scanner scanner, PhoneBook phoneBook) {
        this.scanner = scanner;
        this.memoryPhoneBook = phoneBook;
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

        memoryPhoneBook.addContact(contact);
        System.out.println("The record added.");
    }

    private void printContactsCount() {
        System.out.println("The Phone Book has " + memoryPhoneBook.getContacts().size() + " records.");
    }

    private void printContactIndexList() {
        memoryPhoneBook.getContactIndexInformation().forEach(System.out::println);
    }

    private void printContactInfo() {
        if (memoryPhoneBook.getContacts().isEmpty()) {
            System.out.println("No records to show!");
            return;
        }

        int index = getContactIndex();
        String info = memoryPhoneBook.getContactInformation(index);
        System.out.println(info);
    }

    private void editContactsInstructions() {
        if (memoryPhoneBook.getContacts().isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        memoryPhoneBook.editContactInformation(getContactIndex(), scanner);
        System.out.println("The record updated!");
    }

    private void removeContact() {
        if (memoryPhoneBook.getContacts().isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }

        memoryPhoneBook.removeContact(getContactIndex());
        System.out.println("The record removed!");
    }

    private int getContactIndex() {
        printContactIndexList();
        System.out.print("Select a record: ");
        return Integer.parseInt(scanner.nextLine()) - 1;
    }
}
