package contacts.phonebook;

import contacts.contact.OrganizationContact;
import contacts.contact.Person;
import contacts.contact.PersonContact;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MemoryPhoneBookTest {

    private final MemoryPhoneBook memoryPhoneBook = new MemoryPhoneBook();

    @Test
    void getListReturnsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> memoryPhoneBook.getContacts().add(null));
    }

    @Test
    void addContact() {
        memoryPhoneBook.addContact(new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345"));
        assertEquals(1, memoryPhoneBook.getContacts().size());
    }

    @Test
    void addMultipleContacts() {
        memoryPhoneBook.addContact(new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345"));
        memoryPhoneBook.addContact(new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345"));
        memoryPhoneBook.addContact(new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345"));
        assertEquals(3, memoryPhoneBook.getContacts().size());
    }

    @Test
    void addingNullContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> memoryPhoneBook.addContact(null));
    }

    @Test
    void editCorrectContact() {
        memoryPhoneBook.addContact(new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345"));
        memoryPhoneBook.addContact(new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345"));
        memoryPhoneBook.addContact(new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345"));
        String expectedBeforeChange = "John Doe";
        assertEquals(expectedBeforeChange, memoryPhoneBook.getContacts().get(0).getFullName());

        Scanner scanner = new Scanner("name\nSmith");
        memoryPhoneBook.editContactInformation(0, scanner);

        String expectedAfterChange = "Smith Doe";
        assertEquals(expectedAfterChange, memoryPhoneBook.getContacts().get(0).getFullName());
    }

    @Test
    void removeContact() {
        memoryPhoneBook.addContact(new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345"));
        memoryPhoneBook.addContact(new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345"));
        memoryPhoneBook.addContact(new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345"));
        memoryPhoneBook.removeContact(0);

        int expected = 2;
        assertEquals(expected, memoryPhoneBook.getContacts().size());
    }

    @Test
    void removeCorrectContact() {
        memoryPhoneBook.addContact(new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345"));
        memoryPhoneBook.addContact(new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345"));
        memoryPhoneBook.addContact(new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345"));
        memoryPhoneBook.removeContact(0);

        String expected = "Pizza Shop";
        assertEquals(expected, memoryPhoneBook.getContacts().get(0).getFullName());
    }
}