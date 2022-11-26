package contacts;

import contacts.contact.OrganizationContact;
import contacts.contact.Person;
import contacts.contact.PersonContact;
import contacts.phonebook.MemoryPhoneBook;
import contacts.phonebook.PhoneBook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchEngineTest {

    PhoneBook phoneBook = new MemoryPhoneBook();
    SearchEngine searchEngine;

    static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void setup() {
        outputStream.reset();
        phoneBook.addContact(new OrganizationContact("Pizza", "Str", "123"));
        phoneBook.addContact(new OrganizationContact("Game", "Str", "123"));
        phoneBook.addContact(new OrganizationContact("Sky", "Str", "123"));
        phoneBook.addContact(new PersonContact(new Person("John", "Doe", "1999-1-1", "m"), "12"));
        phoneBook.addContact(new PersonContact(new Person("Pizza", "doe", "1999-1-1", "m"), "12"));
    }

    @Test
    @DisplayName("Search return correct contacts")
    void searchContact() {
        generateSearchEngineFromInput("e\nback\n");
        searchEngine.search();

        String expectedOutput = "Enter search query: " +
                "Found 3 result: \r\n" +
                "1. Game\r\n" +
                "2. John Doe\r\n" +
                "3. Pizza doe\r\n\r\n" +
               "[search] Enter action ([number], back, again): ";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Search return correct contacts using regex")
    void searchSupportRegex() {
        generateSearchEngineFromInput(".*\nback\n");
        searchEngine.search();

        String expectedOutput = "Enter search query: " +
                "Found 5 result: \r\n" +
                "1. Pizza\r\n" +
                "2. Game\r\n" +
                "3. Sky\r\n" +
                "4. John Doe\r\n" +
                "5. Pizza doe\r\n\r\n" +
                "[search] Enter action ([number], back, again): ";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Search is case insensitive")
    void searchRegexCaseInsensitive() {
        generateSearchEngineFromInput("doe$\nback\n");
        searchEngine.search();

        String expectedOutput = "Enter search query: " +
                "Found 2 result: \r\n" +
                "1. John Doe\r\n" +
                "2. Pizza doe\r\n\r\n" +
                "[search] Enter action ([number], back, again): ";

        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    @DisplayName("Search again continuely for new results")
    void searchAgainReturnNewResult() {
        generateSearchEngineFromInput("e\nagain\n^pizza\nback\n");
        searchEngine.search();

        String expectedOutput = "Enter search query: " +
                "Found 3 result: \r\n" +
                "1. Game\r\n" +
                "2. John Doe\r\n" +
                "3. Pizza doe\r\n\r\n" +
                "[search] Enter action ([number], back, again): " +
                "Enter search query: " +
                "Found 2 result: \r\n" +
                "1. Pizza\r\n" +
                "2. Pizza doe\r\n\r\n" +
                "[search] Enter action ([number], back, again): ";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Show correct record info on number input")
    void showContactInfo() {
        generateSearchEngineFromInput("^Pizza\n2\n");
        searchEngine.search();


        String expectedOutput = "Enter search query: " +
                "Found 2 result: \r\n" +
                "1. Pizza\r\n" +
                "2. Pizza doe\r\n\r\n" +
                "[search] Enter action ([number], back, again): " +
                "Name: Pizza\n" +
                "Surname: doe\n" +
                "Birth date: 1999-1-1\n" +
                "Gender: M\n" +
                "Number: 12\n" +
                "Time created: ";

        assertTrue(outputStream.toString().startsWith(expectedOutput));

    }

    private void generateSearchEngineFromInput(String input) {
        searchEngine = new SearchEngine(phoneBook, new Scanner(input));
    }
}
