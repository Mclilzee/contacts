package contacts.phonebook;

import contacts.contact.Contact;
import contacts.contact.OrganizationContact;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DiskPhoneBookTest extends PhoneBookBaseTest {

    File file = new File("./src/test/java/contacts/phonebook/test.data");

    @Override
    protected PhoneBook createInstance() {
        return new DiskPhoneBook(file);
    }
}

