package contacts.options;

import contacts.Contact;
import contacts.PhoneBook;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ContactOption {

    private final Scanner scanner;
    PhoneBook phoneBook = new PhoneBook();

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

    private void printRecordsCount() {
        System.out.printf("The Phone Book has %d records.%n", phoneBook.getContacts().size());
    }

    private void printInstructions() {
        System.out.print("Enter action (add, remove, edit, count, list, exit): ");
    }

    private void addNewContact() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the number: ");
        String number = scanner.nextLine();

        phoneBook.addContact(new Contact(name, surname, number));
    }
}
