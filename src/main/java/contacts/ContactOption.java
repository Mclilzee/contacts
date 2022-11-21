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
                case "add":
                    addNewContact();
                    break;
                case "edit":
                    editRecordsInstructions();
                    break;
            }
        }
    }

    public void printInstructions() {
        System.out.print("Enter action (add, remove, edit, count, list, exit): ");
    }

    public void addNewContact() {
        String name = getInput("Enter the name: ");
        String surname = getInput("Enter the surname: ");
        String number = getInput("Enter the number: ");

        try {
            phoneBook.addContact(new Contact(name, surname, number));
        } catch (IllegalArgumentException ignored) {
            System.out.println("The record added.");
        }
    }

    public void printRecordsCount() {
        System.out.println("The Phone Book has " + phoneBook.getContacts().size() + " records.");
    }

    public void printRecordsList() {
        phoneBook.getRecordsInformation().forEach(System.out::println);
    }

    public void editRecordsInstructions() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        printRecordsList();
        int index = Integer.parseInt(getInput("Select a record: ")) - 1;
        String field = getInput("Select a field (name, surname, number): ");
        editRecord(index, field);
    }

    public void editRecord(int index, String field) {
        switch (field) {
            case "name" -> phoneBook.setName(index, field);
            case "surname" -> phoneBook.setSurname(index, field);
            case "number" -> phoneBook.setPhoneNumber(index, field);
            default -> throw new IllegalArgumentException();
        }
    }

    private void removeRecord() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        printRecordsList();
        int index = Integer.parseInt(getInput("Select a record: ")) - 1;
        phoneBook.removeRecord(index);
        System.out.println("The record removed!");
    }

    private String getInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
