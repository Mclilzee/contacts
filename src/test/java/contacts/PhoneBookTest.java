package contacts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneBookTest {

    private final PhoneBook phoneBook = new PhoneBook();
    private final Contact firstContact = new Contact("John", "Doe", "+0 (123) 12345");
    private final Contact secondContact = new Contact("Mark", "Dobless", "+0 (123) 12345");
    private final Contact thirdContact = new Contact("Emad", "Doblos", "+0 (123) 12345");

    @Test
    void getListReturnsUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> phoneBook.getContacts().add(firstContact));
    }

    @Test
    void addContact() {
        phoneBook.addContact(firstContact);
        assertEquals(1, phoneBook.getContacts().size());
    }

    @Test
    void addMultipleContacts() {
        phoneBook.addContact(firstContact);
        phoneBook.addContact(secondContact);
        phoneBook.addContact(thirdContact);
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
        phoneBook.addContact(firstContact);
        phoneBook.addContact(secondContact);

        List<String> expected = List.of(
                "1. John Doe, +0 (123) 12345",
                "2. Mark Dobless, +0 (123) 12345"
        );
        assertEquals(expected, phoneBook.getRecordsInformation());
    }

}