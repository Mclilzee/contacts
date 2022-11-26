package contacts.phonebook;

import contacts.contact.Contact;
import contacts.contact.OrganizationContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DiskPhoneBookTest {

    File file = new File("./src/test/java/contacts/phonebook/test.data");
    PhoneBook phoneBook = new DiskPhoneBook(file);

    @BeforeEach
    void setup() {
        file.delete();
    }

    @Test
    void getEmptyListIfNoContactsExist() {
        List<Contact> contacts = phoneBook.getContacts();
        assertTrue(contacts.isEmpty());
    }

    @Test
    void addContactCorrectly() {
        Contact contact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        phoneBook.addContact(contact);
        List<Contact> contacts = phoneBook.getContacts();
        assertEquals(1, contacts.size());
    }

    @Test
    void loadOldContactFromFile() {
        Contact contact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        phoneBook.addContact(contact);

        phoneBook = new DiskPhoneBook(file);
        List<Contact> contacts = phoneBook.getContacts();
        assertEquals(1, contacts.size());
    }

    @Test
    void addingContactPersistInDatabase() {
        Contact contact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        phoneBook.addContact(contact);
        phoneBook.addContact(contact);

        phoneBook = new DiskPhoneBook(file);
        List<Contact> contacts = phoneBook.getContacts();
        assertEquals(2, contacts.size());
    }

    @Test
    void editTheCorrectContact() {
        Contact contact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        Contact secondContact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        phoneBook.addContact(contact);
        phoneBook.addContact(secondContact);


        phoneBook.editContactInformation(secondContact, new Scanner("number\n2222\n"));

        List<Contact> contacts = phoneBook.getContacts();
        String expected = "2222";
        assertEquals(expected, contacts.get(1).getPhoneNumber());
        assertNotEquals(expected, contacts.get(0).getPhoneNumber());
    }

    @Test
    void editedContactPersistInDatabase() {
        Contact contact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        Contact secondContact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        phoneBook.addContact(contact);
        phoneBook.addContact(secondContact);


        phoneBook.editContactInformation(secondContact, new Scanner("number\n2222\n"));
        phoneBook = new DiskPhoneBook(file);

        List<Contact> contacts = phoneBook.getContacts();
        String expected = "2222";
        assertEquals(expected, contacts.get(1).getPhoneNumber());
        assertNotEquals(expected, contacts.get(0).getPhoneNumber());
    }

    @Test
    void removeContact() {
        Contact contact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        Contact secondContact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        phoneBook.addContact(contact);
        phoneBook.addContact(secondContact);

        phoneBook.removeContact(secondContact);

        List<Contact> contacts = phoneBook.getContacts();
        int expected = 1;
        assertEquals(expected, contacts.size());
    }

    @Test
    void removedContactPersistInDatabase() {
        Contact contact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        Contact secondContact = new OrganizationContact("Pizza Store", "Wall str 1", "123");
        phoneBook.addContact(contact);
        phoneBook.addContact(secondContact);


        phoneBook.removeContact(secondContact);
        phoneBook = new DiskPhoneBook(file);

        List<Contact> contacts = phoneBook.getContacts();
        int expected = 1;
        assertEquals(expected, contacts.size());
    }
}

