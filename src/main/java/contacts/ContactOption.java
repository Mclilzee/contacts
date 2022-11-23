package contacts;

import contacts.contact.Contact;
import contacts.factory.ContactFactory;
import contacts.factory.OrganizationContactFactory;
import contacts.factory.PersonContactFactory;

import static contacts.util.InputUtil.getInput;

public class ContactOption {

    private final PhoneBook phoneBook = new PhoneBook();
    private final ContactFactory personFactory;
    private final ContactFactory organizationFactory;

    public ContactOption() {
        this.personFactory = new PersonContactFactory();
        this.organizationFactory = new OrganizationContactFactory();
    }

    public void start() {
        while (true) {
            switch (getInput("Enter action (add, remove, edit, count, list, exit): ")) {
                case "exit":
                    return;
                case "count":
                    printRecordsCount();
                    break;
                case "list":
                    printRecordsList();
                    break;
                case "add":
                    addNewContact();
                    break;
                case "edit":
                    editRecordsInstructions();
                    break;
                case "remove":
                    removeRecord();
                    break;
            }
        }
    }

    private void addNewContact() {
        String type = getInput("Enter the type: (person, organization): ");
        Contact contact;
        if ("person".equalsIgnoreCase(type)) {
            contact = personFactory.createContact();
        } else {
            contact = organizationFactory.createContact();
        }

        phoneBook.addContact(contact);
    }

    private void printRecordsCount() {
        System.out.println("The Phone Book has " + phoneBook.getContacts().size() + " records.");
    }

    private void printRecordsList() {
        phoneBook.getContactIndexInformation().forEach(System.out::println);
    }

    private void editRecordsInstructions() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        printRecordsList();
        int index = Integer.parseInt(getInput("Select a record: ")) - 1;
        phoneBook.editContactInformation(index);
        printUpdatedMessage();
    }

    private void removeRecord() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }

        printRecordsList();
        int index = Integer.parseInt(getInput("Select a record: ")) - 1;
        phoneBook.removeContact(index);
        System.out.println("The record removed!");
    }

    private void printUpdatedMessage() {
        System.out.println("The record updated!");
    }
}
