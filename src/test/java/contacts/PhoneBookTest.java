package contacts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneBookTest {

    private final PhoneBook phoneBook = new PhoneBook();
    private final Contact firstContact = new Contact("John", "Doe", "+0 (123) 12345");
    private final Contact secondContact = new Contact("Mark", "Dobless", "+0 (123) 12345");
    private final Contact thirdContact = new Contact("Emad", "Doblos", "+0 (123) 12345");

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
}