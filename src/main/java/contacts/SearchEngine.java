package contacts;

import contacts.contact.Contact;
import contacts.phonebook.PhoneBook;
import contacts.utils.ContactPrinter;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SearchEngine {

    private final PhoneBook phoneBook;
    private final Scanner scanner;


    public SearchEngine(PhoneBook phoneBook, Scanner scanner) {
        this.phoneBook = phoneBook;
        this.scanner = scanner;
        search();
    }

    public void search() {
        List<Contact> contacts = getContactFromSearchQuery();

        System.out.println("Found " + contacts.size() + " result: ");
        ContactPrinter.printIndexList(contacts);
        System.out.println();
        nextAction(contacts);
    }

    private List<Contact> getContactFromSearchQuery() {
        System.out.print("Enter search query: ");
        String search = scanner.nextLine();
        Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
        return phoneBook.getContacts()
                .stream()
                .filter(contact -> pattern.matcher(contact.getFullName()).find())
                .toList();
    }

    private void nextAction(List<Contact> contacts) {
        System.out.print("[search] Enter action ([number], back, again): ");
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "back":
                return;
            case "again":
                search();
                return;
        }

        if (input.matches("\\d+")) {
            int index = Integer.parseInt(input) - 1;
            new ContactEditor(phoneBook, contacts.get(index), scanner);
        }
    }
}
