package contacts.factory;

import contacts.contact.Contact;
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
        return new PersonContact(new Person("John", "doe", "f", "1922"), "123");
    }

}
