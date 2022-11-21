package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {

    private final Scanner scanner;

    private final List<Contact> contacts = new ArrayList<>();

    public PhoneBook(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addNewContact() {
        contacts.add(getContactFromInput());
        System.out.println("The record added.");
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    private Contact getContactFromInput() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the number: ");
        String number = scanner.nextLine();

        return new Contact(name, surname, number);
    }

    public void printRecordsCount() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

    public void printRecordsList() {
        System.out.println("");
    }
}
