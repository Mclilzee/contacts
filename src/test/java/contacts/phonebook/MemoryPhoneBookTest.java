package contacts.phonebook;

import contacts.contact.Contact;
import contacts.contact.OrganizationContact;
import contacts.contact.Person;
import contacts.contact.PersonContact;
import contacts.phonebook.MemoryPhoneBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MemoryPhoneBookTest {

    private final Person person = new Person("John", "Doe", "1993-12-12", "M");
    private final Contact firstContact = new PersonContact(person , "+0 (123) 12345");
    private final Contact secondContact = new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345");
    private final Contact thirdContact = new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345");
    private final MemoryPhoneBook memoryPhoneBook = new MemoryPhoneBook();

    @Test
    void getListReturnsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> memoryPhoneBook.getContacts().add(null));
    }

    @Test
    void addContact() {
        memoryPhoneBook.addContact(firstContact);
        assertEquals(1, memoryPhoneBook.getContacts().size());
    }

    @Test
    void addMultipleContacts() {
        fillContacts();
        assertEquals(3, memoryPhoneBook.getContacts().size());
    }

    @Test
    void addingNullContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> memoryPhoneBook.addContact(null));
    }

    @Test
    @DisplayName("Contacts list information returns empty list, when no contacts exist")
    void contactListInformationEmpty() {
        List<String> expected = List.of();
        assertEquals(expected, memoryPhoneBook.getContactIndexInformation());
    }

    @Test
    void contactIndexInformationCorrectListReturned() {
        fillContacts();
        List<String> expected = List.of("1. " + firstContact.getFullName(), "2. " + secondContact.getFullName(), "3. " + thirdContact.getFullName());
        assertEquals(expected, memoryPhoneBook.getContactIndexInformation());
    }

    @Test
    void getContactInformation() {
        fillContacts();
        assertEquals(secondContact.getInfo(), memoryPhoneBook.getContactInformation(1));
    }

    @Test
    void editCorrectContact() {
        fillContacts();
        String expectedBeforeChange = "John Doe";
        assertEquals(expectedBeforeChange, memoryPhoneBook.getContacts().get(0).getFullName());

        Scanner scanner = new Scanner("name\nSmith");
        memoryPhoneBook.editContactInformation(0, scanner);

        String expectedAfterChange = "Smith Doe";
        assertEquals(expectedAfterChange, memoryPhoneBook.getContacts().get(0).getFullName());
    }

    @Test
    void removeContact() {
        fillContacts();
        memoryPhoneBook.removeContact(0);
        int expected = 2;
        assertEquals(expected, memoryPhoneBook.getContacts().size());
    }

    @Test
    void removeCorrectContact() {
        fillContacts();
        memoryPhoneBook.removeContact(1);
        List<String> expected = List.of("1. " + firstContact.getFullName(), "2. " + thirdContact.getFullName());

        assertEquals(expected, memoryPhoneBook.getContactIndexInformation());

    }

    private void fillContacts() {
        memoryPhoneBook.addContact(firstContact);
        memoryPhoneBook.addContact(secondContact);
        memoryPhoneBook.addContact(thirdContact);
    }
}