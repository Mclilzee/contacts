package contacts.utils;

import contacts.contact.Contact;
import contacts.contact.OrganizationContact;
import contacts.contact.Person;
import contacts.contact.PersonContact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactPrinterTest {

    List<Contact> contacts = List.of(
            new OrganizationContact("Pizza Store", "Wall str. 1", "1234"),
            new OrganizationContact("Game Store", "Gnarr str. 1", "222"),
            new PersonContact(new Person("John", "Doe", "1999-10-10", "M"), "12345")
    );

    static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void setup() {
        outputStream.reset();
    }

    @Test
    void printContactIndexCorrectly() {
        ContactPrinter.printIndexList(contacts);

        String expected = "1. Pizza Store\r\n2. Game Store\r\n3. John Doe\r\n";
        assertEquals(expected, outputStream.toString());
    }

}