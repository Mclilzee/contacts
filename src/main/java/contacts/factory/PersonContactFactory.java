package contacts.factory;

import contacts.contact.Person;
import contacts.contact.PersonContact;

import java.util.Scanner;

public class PersonContactFactory implements ContactFactory {

    private final Scanner scanner;

    public PersonContactFactory(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public PersonContact createContact() {
        Person person = createPerson();

        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();
        return new PersonContact(person, phoneNumber);
    }

    private Person createPerson() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the birth date: ");
        String birthDate = scanner.nextLine();

        System.out.print("Enter the gender: ");
        String gender = scanner.nextLine();

        return new Person(name, surname, birthDate, gender);
    }

}
