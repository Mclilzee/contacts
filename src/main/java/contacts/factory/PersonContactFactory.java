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

        String phoneNumber = scanner.nextLine();
        return new PersonContact(person, phoneNumber);
    }

    private Person createPerson() {
        String name = scanner.nextLine();
        String surname = scanner.nextLine();
        String birthDate = scanner.nextLine();
        String gender = scanner.nextLine();

        return new Person(name, surname, birthDate, gender);
    }

}
