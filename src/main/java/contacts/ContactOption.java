package contacts;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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
            }
        }
    }

    public void printInstructions() {
        System.out.print("Enter action (add, remove, edit, count, list, exit): ");
    }

    public void addNewContact() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the number: ");
        String number = scanner.nextLine();

        phoneBook.addContact(new Contact(name, surname, number));
        System.out.println("The record added.");
    }

    public void printRecordsCount() {
        System.out.println("The Phone Book has " + phoneBook.getContacts().size() + " records.");
    }

    public void printRecordsList() {
        List<Contact> contacts = phoneBook.getContacts();
        IntStream.range(0, contacts.size())
                .mapToObj(index -> (index + 1) + ". " + contacts.get(index))
                .forEach(System.out::println);
    }

    public void editRecordsInstructions() {
        if (phoneBook.getContacts().isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        printRecordsList();
        System.out.print("Select a record: ");
        int index = scanner.nextInt() - 1;

        processRecordEditInstructions(index);
    }

    public void processRecordEditInstructions(int index) {
        System.out.print("Select a field (name, surname, number): ");
        String input = scanner.nextLine();

        editRecord(index, input);
    }

    public void editRecord(int index, String field) {
        switch (field) {
            case "name":
                break;
            case "surname":
                break;
            case "number":
                break;
        }
    }
}
