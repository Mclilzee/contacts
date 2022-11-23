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
    @DisplayName("Records list information returns empty list, when no records exist")
    void recordListInformationEmpty() {
        List<String> expected = List.of();
        assertEquals(expected, phoneBook.getRecordsInformation());
    }

    @Test
    void recordListInformationCorrectListReturned() {
        fillContacts();
        List<String> expected = List.of("1. " + firstContact.getInfo(), "2. " + secondContact.getInfo(), "3. " + thirdContact.getInfo());
        assertEquals(expected, phoneBook.getRecordsInformation());
    }

    @Test
    void editCorrectContact() {
        fillContacts();
        phoneBook.editRecordInformation(1);
        
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
        List<String> expected = List.of("1. " + firstContact.getInfo(), "2. " + secondContact.getInfo());

        assertEquals(expected, phoneBook.getRecordsInformation());

    }

    private void fillContacts() {
        phoneBook.addContact(firstContact);
        phoneBook.addContact(secondContact);
        phoneBook.addContact(thirdContact);
    }
}