package contacts;

import contacts.contact.Contact;
import contacts.contact.OrganizationContact;
import contacts.contact.Person;
import contacts.contact.PersonContact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {

    private final Person person = new Person("John", "Doe", "M", "1993-12-12");
    private final Contact firstContact = new PersonContact(person , "+0 (123) 12345");
    private final Contact secondContact = new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345");
    private final Contact thirdContact = new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345");
    private final PhoneBook phoneBook = new PhoneBook();

    @Test
    void getListReturnsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> phoneBook.getContacts().add(null));
    }

    @Test
    void addContact() {
        phoneBook.addContact(firstContact);
        assertEquals(1, phoneBook.getContacts().size());
    }

    @Test
    void addMultipleContacts() {
        fillContacts();
        assertEquals(3, phoneBook.getContacts().size());
    }

    @Test
    void addingNullContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> phoneBook.addContact(null));
    }

    @Test
    @DisplayName("Contacts list information returns empty list, when no contacts exist")
    void contactListInformationEmpty() {
        List<String> expected = List.of();
        assertEquals(expected, phoneBook.getContactIndexInformation());
    }

    @Test
    void contactIndexInformationCorrectListReturned() {
        fillContacts();
        List<String> expected = List.of("1. " + firstContact.getFullName(), "2. " + secondContact.getFullName(), "3. " + thirdContact.getFullName());
        assertEquals(expected, phoneBook.getContactIndexInformation());
    }

    @Test
    void getContactInformation() {
        fillContacts();
        assertEquals(secondContact.getInfo(), phoneBook.getContactInformation(2));
    }

    @Test
    void editCorrectContact() {
        fillContacts();
        String expectedBeforeChange = "John Doe";
        assertEquals(expectedBeforeChange, phoneBook.getContacts().get(0).getFullName());

        Scanner scanner = new Scanner("name\nSmith");
        phoneBook.editContactInformation(0, scanner);

        String expectedAfterChange = "Smith Doe";
        assertEquals(expectedAfterChange, phoneBook.getContacts().get(0).getFullName());
    }

    @Test
    void removeContact() {
        fillContacts();
        phoneBook.removeContact(0);
        int expected = 2;
        assertEquals(expected, phoneBook.getContacts().size());
    }

    @Test
    void removeCorrectContact() {
        fillContacts();
        phoneBook.removeContact(1);
        List<String> expected = List.of("1. " + firstContact.getFullName(), "2. " + thirdContact.getFullName());

        assertEquals(expected, phoneBook.getContactIndexInformation());

    }

    private void fillContacts() {
        phoneBook.addContact(firstContact);
        phoneBook.addContact(secondContact);
        phoneBook.addContact(thirdContact);
    }
}