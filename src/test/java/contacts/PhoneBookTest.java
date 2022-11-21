package contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneBookTest {

    private final String RECORD_ADDED_OUTPUT = "The record added.\r\n";
    private final PhoneBook phoneBook = new PhoneBook();
    private final Contact firstContact = new Contact("John", "Doe", "+0 (123) 12345");
    private final Contact secondContact = new Contact("Mark", "Dobless", "+0 (123) 12345");
    private final Contact thirdContact = new Contact("Emad", "Seblos", "+0 (123) 12345");

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
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
    void addContactPrintCorrectOutput() {
        phoneBook.addContact(firstContact);
        assertEquals(RECORD_ADDED_OUTPUT, outputStreamCaptor.toString());
    }

    @Test
    void nullIsNotAdded() {
        phoneBook.addContact(null);
        assertEquals(0, phoneBook.getContacts().size());
    }
}