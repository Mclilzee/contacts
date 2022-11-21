package contacts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneBookTest {

    private final String firstContactInformation = "John\nDoe\n+0 (123) 12345\n";
    private final String secondContactInformation = "Mark\nDobless\n+0 (123) 12345\n";
    private final String thirdContactInformation = "Emad\nDoblos\n+0 (123) 12345\n";

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void resetStream() {
        outputStream.reset();
    }

    @Test
    void addContact() {
        Scanner scanner = new Scanner(firstContactInformation);
        PhoneBook phoneBook = new PhoneBook(scanner);
        phoneBook.addNewContact();
        assertEquals(1, phoneBook.getContacts().size());
    }

    @Test
    void addMultipleContacts() {
        Scanner scanner = new Scanner(firstContactInformation + secondContactInformation + thirdContactInformation);
        PhoneBook phoneBook = new PhoneBook(scanner);
        phoneBook.addNewContact();
        phoneBook.addNewContact();
        phoneBook.addNewContact();
        assertEquals(3, phoneBook.getContacts().size());
    }

    @Test
    void addContactPrintCorrectOutput() {
        Scanner scanner = new Scanner(firstContactInformation);
        PhoneBook phoneBook = new PhoneBook(scanner);
        phoneBook.addNewContact();

        String expected = "Enter the name: Enter the surname: Enter the number: The record added.\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void printCorrectRecordCountMultipleContacts() {
        Scanner scanner = new Scanner(firstContactInformation + secondContactInformation);
        PhoneBook phoneBook = new PhoneBook(scanner);
        phoneBook.addNewContact();
        phoneBook.addNewContact();


        outputStream.reset();
        String expected = "The Phone Book has 2 records.\r\n";
        phoneBook.printRecordsCount();
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void printCorrectRecordCountInformation() {
        Scanner scanner = new Scanner("");
        PhoneBook phoneBook = new PhoneBook(scanner);
        phoneBook.printRecordsCount();

        String expected = "The Phone Book has 0 records.\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void printListInformation() {
        Scanner scanner = new Scanner("");
        PhoneBook phoneBook = new PhoneBook(scanner);
        phoneBook.printRecordsList();

        assertEquals("", outputStream.toString());
    }

    @Test
    void printCorrectListInformationForContactWithBooks() {
        Scanner scanner = new Scanner(firstContactInformation + secondContactInformation + thirdContactInformation);
        PhoneBook phoneBook = new PhoneBook(scanner);
        phoneBook.addNewContact();
        phoneBook.addNewContact();
        phoneBook.addNewContact();

        outputStream.reset();
        phoneBook.printRecordsList();
        String expected = "1. John Doe, +0 (123) 12345\r\n" +
                "2. Mark Dobless, +0 (123) 12345\r\n" +
                "3. Emad Doblos, +0 (123) 12345\r\n";

        assertEquals(expected, outputStream.toString());
    }
}