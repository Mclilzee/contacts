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

        String expectedOutput = "Enter search query: " +
                "Found 3 result:\r\n" +
                "1. Game Str\r\n" +
                "2. John Doe\r\n" +
                "3. Pizza doe\r\n\r\n" +
               "[search] Enter action ([number], back, again): ";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    @DisplayName("Remove contact when list is empty show correct output")
    void removeEmptyContact() {

    }

    @Test
    @DisplayName("Remove contact return correct output message")
    void removeContact() {

    }


    @Test
    @DisplayName("Edit empty contact list will output correct message")
    void editEmptyContacts() {
    }

    @Test
    @DisplayName("Edit contact will output the correct message")
    void editContacts() {
    }

    private void generateSearchEngineFromInput(String input) {
        searchEngine = new SearchEngine(phoneBook, new Scanner(input));
    }
}
