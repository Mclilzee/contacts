package contacts;

import contacts.contact.Contact;
import contacts.contact.OrganizationContact;
import contacts.contact.Person;
import contacts.contact.PersonContact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneBookTest {

    private final PhoneBook phoneBook = new PhoneBook();

    @Test
    void getListReturnsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> phoneBook.getContacts().add(null));
    }

    @Test
    void addContact() {
        Contact contact = new OrganizationContact("Pizza", "Street", "+0 (123) 12345");
        phoneBook.addContact(contact);
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
    @DisplayName("Records list information returns empty list, when no records exist")
    void recordListInformationEmpty() {
        List<String> expected = List.of();
        assertEquals(expected, phoneBook.getRecordsInformation());
    }

    @Test
    void recordListInformationCorrectListReturned() {
        fillContacts();
        List<String> expected = List.of(
                "1. John Doe, +0 (123) 12345",
                "2. Mark Dobless, +0 (123) 12345",
                "3. Emad Doblos, +0 (123) 12345"
        );
        assertEquals(expected, phoneBook.getRecordsInformation());
    }


    @Test
    void setPhoneNumberFirstIndex() {
        fillContacts();
        phoneBook.setPhoneNumber(0, "+0 (234) 432");
        String expected = "+0 (234) 432";
        assertEquals(expected, phoneBook.getContacts().get(0).getPhoneNumber());
    }

    @Test
    void setPhoneNumberLastIndex() {
        fillContacts();
        phoneBook.setPhoneNumber(2, "+0 (234) 4321");
        String expected = "+0 (234) 4321";
        assertEquals(expected, phoneBook.getContacts().get(2).getPhoneNumber());
    }

    @Test
    void removeRecord() {
        fillContacts();
        phoneBook.removeRecord(0);
        int expected = 2;
        assertEquals(expected, phoneBook.getContacts().size());
    }

    @Test
    void removeCorrectRecord() {
        fillContacts();
        phoneBook.removeRecord(1);
        List<String> expected = List.of(
                "1. John Doe, +0 (123) 12345",
                "2. Emad Doblos, +0 (123) 12345"
        );

        assertEquals(expected, phoneBook.getRecordsInformation());

    }

    private void fillContacts() {
        Person person = new Person("John", "Doe", "M", "1993-12-12");
        Contact firstContact = new PersonContact(person , "+0 (123) 12345");

        Contact secondContact = new OrganizationContact("Pizza Shop", "Wall St. 1", "+0 (123) 12345");
        Contact thirdContact = new OrganizationContact("Game Store", "Gnarren Str. 15", "+0 (123) 12345");
        phoneBook.addContact(firstContact);
        phoneBook.addContact(secondContact);
        phoneBook.addContact(thirdContact);
    }
}