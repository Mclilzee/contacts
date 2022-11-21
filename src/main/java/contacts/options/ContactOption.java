package contacts.options;

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
                   printContactsCount();
                   break;
           }
       }
    }

    private void printContactsCount() {
        System.out.println("The Phone book has " + phoneBook.getContacts().size() + " records.");
    }

    private void printInstructions() {
        System.out.print("Enter action (add, remove, edit, count, list, exit): ");
    }
}
