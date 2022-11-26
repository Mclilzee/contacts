package contacts.phonebook;

import contacts.contact.OrganizationContact;
import contacts.contact.Person;
import contacts.contact.PersonContact;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemoryPhoneBookTest {

    PhoneBook phonebook = new MemoryPhoneBook();

    @Test
    void getContactReturnEmptyListWhenEmpty() {
        assertEquals(List.of(), phonebook.getContacts());
    }

    @Test
    void getListReturnsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> phonebook.getContacts().add(null));
    }

    @Test
    void addContact() {
        phonebook.addContact(new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345"));
        assertEquals(1, phonebook.getContacts().size());
    }

    @Test
    void addMultipleContacts() {
        phonebook.addContact(new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345"));
        phonebook.addContact(new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345"));
        phonebook.addContact(new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345"));
        assertEquals(3, phonebook.getContacts().size());
    }

    @Test
    void addingNullContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> phonebook.addContact(null));
    }

    @Test
    void editCorrectContact() {
        PersonContact contact = new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345");
        phonebook.addContact(contact);
        phonebook.addContact(new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345"));
        phonebook.addContact(new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345"));
        String expectedBeforeChange = "John Doe";
        assertEquals(expectedBeforeChange, phonebook.getContacts().get(0).getFullName());

        Scanner scanner = new Scanner("name\nSmith");
        phonebook.editContactInformation(contact, scanner);

        String expectedAfterChange = "Smith Doe";
        assertEquals(expectedAfterChange, phonebook.getContacts().get(0).getFullName());
    }

    @Test
    void removeContact() {
        PersonContact firstContact = new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345");
        OrganizationContact secondContact = new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345");
        OrganizationContact thirdContact = new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345");
        phonebook.addContact(firstContact);
        phonebook.addContact(secondContact);
        phonebook.addContact(thirdContact);

        phonebook.removeContact(secondContact);

        int expected = 2;
        assertEquals(expected, phonebook.getContacts().size());
    }

    @Test
    void removeCorrectContact() {
        PersonContact firstContact = new PersonContact(new Person("John", "Doe", "1993-12-12", "M"),
                "+0 (123) 12345");
        OrganizationContact secondContact = new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345");
        OrganizationContact thirdContact = new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345");

        phonebook.addContact(firstContact);
        phonebook.addContact(secondContact);
        phonebook.addContact(thirdContact);

        phonebook.removeContact(firstContact);

        String expected = "Pizza Shop";
        assertEquals(expected, phonebook.getContacts().get(0).getFullName());
    }
}