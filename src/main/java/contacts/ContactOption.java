package contacts;

import contacts.contact.Contact;
import contacts.factory.ContactFactory;
import contacts.factory.OrganizationContactFactory;
import contacts.factory.PersonContactFactory;

import java.util.Scanner;

public class ContactOption {

    private final PhoneBook phoneBook = new PhoneBook();
    private final ContactFactory personFactory;
    private final ContactFactory organizationFactory;
    private final Scanner scanner;

    public ContactOption(Scanner scanner) {
        this.scanner = scanner;
        this.personFactory = new PersonContactFactory(scanner);
        this.organizationFactory = new OrganizationContactFactory(scanner);
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
        }
    }

    private void addNewContact() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine().toLowerCase();
        Contact contact;
        if ("person".equals(type)) {
            contact = personFactory.createContact();
        } else {
            contact = organizationFactory.createContact();
        }

        phoneBook.addContact(contact);
    }

    private void printContactsCount() {
        System.out.println("The Phone Book has " + phoneBook.getContacts().size() + " records.");
    }

    private void printContactIndexList() {
        phoneBook.getContactIndexInformation().forEach(System.out::println);
    }

    private void printContactInfo() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to show!");
            return;
        }

        int index = getContactIndex();
        String info = phoneBook.getContactInformation(index);
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
