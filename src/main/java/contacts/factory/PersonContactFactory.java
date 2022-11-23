package contacts.factory;

import contacts.Contact;
import contacts.Person;
import contacts.PersonContact;

public class PersonContactFactory implements ContactFactory {

    public PersonContactFactory() {
    }

    @Override
    public Contact createContact() {
        return new PersonContact(new Person("John", "doe", "f", "1922"), "123");
    }

}
