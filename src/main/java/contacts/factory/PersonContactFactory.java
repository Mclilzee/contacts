package contacts.factory;

import contacts.contact.Contact;
import contacts.contact.Person;
import contacts.contact.PersonContact;

public class PersonContactFactory implements ContactFactory {

    public PersonContactFactory() {
    }

    @Override
    public Contact createContact() {
        return new PersonContact(new Person("John", "doe", "f", "1922"), "123");
    }

}
