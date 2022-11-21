package contacts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactOptionTest {


    private Scanner scanner = new Scanner("");
    private ContactOption contactOption = new ContactOption(scanner);
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private final String firstContactInformation = "John\nDoe\n+0 (123) 12345\n";
    private final String secondContactInformation = "Mark\nDobless\n+0 (123) 12345\n";
    private final String thirdContactInformation = "Emad\nDoblos\n+0 (123) 12345\n";

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void setUp() {
        outputStream.reset();
    }

    @Test
    void printCorrectInstructions() {
       contactOption.printInstructions();
       String expected = "Enter action (add, remove, edit, count, list, exit): ";
       assertEquals(expected, outputStream.toString());
    }

    @Test
    void addContactPrintCorrectOutput() {
        scanner = new Scanner(firstContactInformation);
        contactOption = new ContactOption(scanner);
        contactOption.addNewContact();

        String expected = "Enter the name: Enter the surname: Enter the number: The record added.\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void printCorrectRecordCountInformation() {
        contactOption.printRecordsCount();

        String expected = "The Phone Book has 0 records.\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void printCorrectRecordCountMultipleContacts() {
        scanner = new Scanner(firstContactInformation + secondContactInformation);
        contactOption = new ContactOption(scanner);
        contactOption.addNewContact();
        contactOption.addNewContact();


        outputStream.reset();
        contactOption.printRecordsCount();
        String expected = "The Phone Book has 2 records.\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void printListInformation() {
        contactOption.printRecordsList();
        assertEquals("", outputStream.toString());
    }

    @Test
    void printCorrectListInformationForContactWithBooks() {
        Scanner scanner = new Scanner(firstContactInformation + secondContactInformation + thirdContactInformation);
        contactOption = new ContactOption(scanner);
        contactOption.addNewContact();
        contactOption.addNewContact();
        contactOption.addNewContact();

        outputStream.reset();
        contactOption.printRecordsList();
        String expected = "1. John Doe, +0 (123) 12345\r\n" +
                "2. Mark Dobless, +0 (123) 12345\r\n" +
                "3. Emad Doblos, +0 (123) 12345\r\n";

        assertEquals(expected, outputStream.toString());
    }

    @Test
    void editRecordsShowCorrectMessageIfEmpty() {
        contactOption.editRecordsInstructions();
        String expected = "No records to edit!\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void editRecordsWithTwoRecords() {
        Scanner scanner = new Scanner(firstContactInformation + secondContactInformation);
        contactOption = new ContactOption(scanner);
        contactOption.addNewContact();
        contactOption.addNewContact();
        outputStream.reset();

        try {
            contactOption.editRecordsInstructions();
        } catch (NoSuchElementException ignore) {

        }

        String expected = "1. John Doe, +0 (123) 12345\r\n" +
                "2. Mark Dobless, +0 (123) 12345\r\n";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void testEditName() {

    }
}