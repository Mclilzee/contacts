package contacts;

import java.util.Scanner;

public class ContactOption {

    private final Scanner scanner;
    private final PhoneBook phoneBook = new PhoneBook();

    public ContactOption(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            printInstructions();
            switch (scanner.nextLine().toLowerCase()) {
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

    private void printInstructions() {
        System.out.print("Enter action (add, remove, edit, count, list, exit): ");
    }

    private void addNewContact() {
        String name = getInput("Enter the name: ");
        String surname = getInput("Enter the surname: ");
        String number = getInput("Enter the number: ");

        try {
            phoneBook.addContact(new Contact(name, surname, number));
            System.out.println("The record added.");
        } catch (IllegalArgumentException ignored) {
        }
    }

    private void printRecordsCount() {
        System.out.println("The Phone Book has " + phoneBook.getContacts().size() + " records.");
    }

    private void printRecordsList() {
        phoneBook.getRecordsInformation().forEach(System.out::println);
    }

    private void editRecordsInstructions() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        printRecordsList();
        int index = Integer.parseInt(getInput("Select a record: ")) - 1;
        phoneBook.editRecordInformation(index, scanner);
        printUpdatedMessage();
    }

    private void removeRecord() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }

        printRecordsList();
        int index = Integer.parseInt(getInput("Select a record: ")) - 1;
        phoneBook.removeRecord(index);
        System.out.println("The record removed!");
    }

    private String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private void printUpdatedMessage() {
        System.out.println("The record updated!");
    }
}
