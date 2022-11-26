package contacts.phonebook;

import contacts.contact.Contact;
import contacts.contact.OrganizationContact;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DiskPhoneBookTest {

    File file = new File("./src/test/java/contacts/phonebook/test.data");
    DiskPhoneBook phoneBook = new DiskPhoneBook(file);

    @Test
    void addContact() {
        phoneBook.addContact(new OrganizationContact("something", "something" , "123"));
    }

    @Test
    void getContacts() {
    }

    @Test
    void getContactIndexInformation() {
    }

    @Test
    void getContactInformation() {
    }

    @Test
    void editContactInformation() {
    }

    @Test
    void removeContact() {
    }
}