package contacts;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateContacts {

    private final Scanner scanner;

    public CreateContacts(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createContacts() {
        System.out.println("Enter the name of the person:");
        String name = scanner.nextLine();

        System.out.println("Enter the surname of the person:");
        String surname = scanner.nextLine();

        System.out.println("Enter the number:");
        String number = scanner.nextLine();

        Contact contact = new Contact(name, surname, number);
        System.out.println();
        System.out.println("A record created!");
        System.out.println("A Phone Book with a single record created!");
    }

}
