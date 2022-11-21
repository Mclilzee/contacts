package contacts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneBookTest {

    private final PhoneBook phoneBook = new PhoneBook();

    @Test
    void getListReturnsUnmodifiable() {
        Contact contact = new Contact("John", "Doe", "+0 (123) 12345");
        assertThrows(UnsupportedOperationException.class, () -> phoneBook.getContacts().add(contact));
    }

    @Test
    void addContact() {
        Contact contact = new Contact("John", "Doe", "+0 (123) 12345");
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
    void setFirstNameFirstIndex() {
        fillContacts();
        phoneBook.setName(0, "Doll");
        String expected = "Doll";
        assertEquals(expected, phoneBook.getContacts().get(0).getName());
    }

    @Test
    void setFirstNameLastIndex() {
        fillContacts();
        phoneBook.setName(2, "Marksman");
        String expected = "Marksman";
        assertEquals(expected, phoneBook.getContacts().get(2).getName());
    }

    @Test
    void setSurnameFirstIndex() {
        fillContacts();
        phoneBook.setSurname(0, "Doll");
        String expected = "Doll";
        assertEquals(expected, phoneBook.getContacts().get(0).getSurname());
    }

    @Test
    void setSurnameLastIndex() {
        fillContacts();
        phoneBook.setSurname(2, "Marksman");
        String expected = "Marksman";
        assertEquals(expected, phoneBook.getContacts().get(2).getSurname());
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

    private void fillContacts() {
        Contact firstContact = new Contact("John", "Doe", "+0 (123) 12345");
        Contact secondContact = new Contact("Mark", "Dobless", "+0 (123) 12345");
        Contact thirdContact = new Contact("Emad", "Doblos", "+0 (123) 12345");
        phoneBook.addContact(firstContact);
        phoneBook.addContact(secondContact);
        phoneBook.addContact(thirdContact);
    }
}