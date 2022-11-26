package contacts;

import contacts.contact.Contact;
import contacts.contact.OrganizationContact;
import contacts.contact.Person;
import contacts.contact.PersonContact;
import contacts.phonebook.MemoryPhoneBook;
import contacts.phonebook.PhoneBook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ContactEditorTest {

    ContactEditor contactEditor;
    PhoneBook phoneBook = new MemoryPhoneBook();
    OrganizationContact firstContact = new OrganizationContact("Pizza", "Str", "123");
    OrganizationContact secondContact = new OrganizationContact("Game", "Str", "123");
    PersonContact thirdContact = new PersonContact(new Person("John", "Doe", "1999-1-1", "m"), "12");

    static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void setup() {
        outputStream.reset();
        phoneBook.addContact(firstContact);
        phoneBook.addContact(secondContact);
        phoneBook.addContact(thirdContact);
    }

    @Test
    void printCorrectContactInformation() {
        generateContactEditorWithInput("menu\n", firstContact);

        String expected = firstContact.getInfo() +
                "\r\n" +
                "[record] Enter action (edit, delete, menu): ";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void outputCorrectMessage() {
        generateContactEditorWithInput("delete\n", secondContact);

        String expectedOutput = secondContact.getInfo() +
                "\r\n" +
                "[record] Enter action (edit, delete, menu): The record removed!\r\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void handleRemoveInputCorrectly() {
        generateContactEditorWithInput("delete\n", secondContact);

        int expectedRecordsNumber = 2;
        assertEquals(expectedRecordsNumber, phoneBook.getContacts().size());
    }

    @Test
    void removeCorrectRecord() {
        generateContactEditorWithInput("delete\n", secondContact);

        int expected = -1;

        assertEquals(expected, phoneBook.getContacts().indexOf(secondContact));
    }

    @Test
    void editRecordOutputCorrectMessages() {
        String oldInfo = firstContact.getInfo();
        generateContactEditorWithInput("edit\nname\nDoblos store\nmenu", firstContact);

        String expected = oldInfo + "\r\n" +
                "[record] Enter action (edit, delete, menu): " +
                "Select a field (name, address, number): " +
                "Enter name: " +
                "Saved\r\n" +
                firstContact.getInfo() + "\r\n" +
                "[record] Enter action (edit, delete, menu): ";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void correctContactEdited() {
        generateContactEditorWithInput("edit\nname\nDoblos store\nmenu", firstContact);

        String expected = "Doblos store";
        assertEquals(expected, firstContact.getFullName());
    }

    void generateContactEditorWithInput(String input, Contact contact) {
        contactEditor = new ContactEditor(phoneBook, contact, new Scanner(input));
    }
}