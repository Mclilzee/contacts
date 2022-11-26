package contacts;

import contacts.phonebook.MemoryPhoneBook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ContactOptionTest {

    private ContactOption contactOption;
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private final String ADDING_PERSON_CONTACT_INPUT = "add\nperson\nJohn\nDoe\n1991-10-10\nM\n+0 (123) 456\n";
    private final String ADDING_ORGANIZATION_CONTACT_INPUT = "add\norganization\nPizza Store\nWall St. 1\n0152221\n";
    private final String TYPE_INSTRUCTIONS = "Enter the type (person, organization): ";

    private final String MAIN_INSTRUCTIONS = "[menu] Enter action (add, list, search, count, exit): ";

    private final String ADDING_PERSON_CONTACT_INSTRUCTIONS = TYPE_INSTRUCTIONS +
            "Enter the name: " +
            "Enter the surname: " +
            "Enter the birth date: " +
            "Enter the gender: " +
            "Enter the number: " +
            "The record added.\r\n\r\n" +
            MAIN_INSTRUCTIONS;

    private final String ADDING_ORGANIZATION_CONTACT_INSTRUCTIONS = TYPE_INSTRUCTIONS +
            "Enter the organization name: " +
            "Enter the address: " +
            "Enter the number: " +
            "The record added.\r\n\r\n" +
            MAIN_INSTRUCTIONS;

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void setup() {
        outputStream.reset();
    }

    @Test
    void optionsExitCorrectly() {
        generateContactOptionInputs("exit\n");
        contactOption.start();
    }

    @Test
    void optionsAreCaseInsensitive() {
        generateContactOptionInputs("eXIt\n");
        contactOption.start();
    }

    @Test
    @DisplayName("Adding person contact output correct instructions")
    void addPersonContact() {
        generateContactOptionInputs(ADDING_PERSON_CONTACT_INPUT + "exit\n");
        contactOption.start();
        String expectedOutput = MAIN_INSTRUCTIONS + ADDING_PERSON_CONTACT_INSTRUCTIONS;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Adding organization contact output correct instructions")
    void addOrganizationContact() {
        generateContactOptionInputs(ADDING_ORGANIZATION_CONTACT_INPUT + "exit\n");
        contactOption.start();
        String expectedOutput = MAIN_INSTRUCTIONS + ADDING_ORGANIZATION_CONTACT_INSTRUCTIONS;
        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    @DisplayName("Counting contacts when list is empty output correct message")
    void countEmptyContacts() {
        generateContactOptionInputs("count\nexit\n");
        contactOption.start();
        String expectedOutput = MAIN_INSTRUCTIONS + "The Phone Book has 0 records.\r\n\r\n" + MAIN_INSTRUCTIONS;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Counting contacts output correct message")
    void countContacts() {
        generateContactOptionInputs(ADDING_PERSON_CONTACT_INPUT + "count\nexit\n");
        contactOption.start();
        String expectedOutput = MAIN_INSTRUCTIONS +
                ADDING_PERSON_CONTACT_INSTRUCTIONS +
                "The Phone Book has 1 records.\r\n\r\n" +
                MAIN_INSTRUCTIONS;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Show contact list when list is empty output correct message")
    void showEmptyContactInfo() {
        generateContactOptionInputs("list\nexit\n");
        contactOption.start();
        String expectedOutput = MAIN_INSTRUCTIONS + "No records to show!\r\n\r\n" + MAIN_INSTRUCTIONS;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Show contact list output correct message")
    void showContactInfo() {
        generateContactOptionInputs(ADDING_ORGANIZATION_CONTACT_INPUT + ADDING_PERSON_CONTACT_INPUT + "list\nexit\n");
        contactOption.start();
        String expectedOutput = MAIN_INSTRUCTIONS +
                ADDING_ORGANIZATION_CONTACT_INSTRUCTIONS +
                ADDING_PERSON_CONTACT_INSTRUCTIONS +
                "1. Pizza Store\r\n" +
                "2. John Doe\r\n\r\n" +
                MAIN_INSTRUCTIONS;
        assertEquals(expectedOutput, outputStream.toString());
    }

    private void generateContactOptionInputs(String input) {
        contactOption = new ContactOption(new Scanner(input), new MemoryPhoneBook());
    }
}