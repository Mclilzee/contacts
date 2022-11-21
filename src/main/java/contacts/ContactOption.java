package contacts;

import contacts.Contact;
import contacts.PhoneBook;

import java.util.Scanner;

public class ContactOption {

    private final Scanner scanner;
    private final PhoneBook phoneBook;

    public ContactOption(Scanner scanner) {
        this.scanner = scanner;
        phoneBook = new PhoneBook(scanner);
    }

    public void start() {
       while (true) {
           printInstructions();
           switch (scanner.nextLine().toLowerCase()) {
               case "exit":
                   return;
               case "count":
                   phoneBook.printRecordsCount();
                   break;
               case "add":
                   phoneBook.addNewContact();
                   break;
           }
       }
    }

    private void printInstructions() {
        System.out.print("Enter action (add, remove, edit, count, list, exit): ");
    }

}
