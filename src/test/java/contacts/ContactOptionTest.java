package contacts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    private final String MAIN_INSTRUCTIONS = "Enter action (add, remove, edit, count, info, exit): ";

    private final String ADDING_PERSON_CONTACT_INSTRUCTIONS =  TYPE_INSTRUCTIONS +
            "Enter the name: " +
            "Enter the surname: " +
            "Enter the birth date: " +
            "Enter the gender: " +
            "Enter the number: " +
            MAIN_INSTRUCTIONS;

    private final String ADDING_ORGANIZATION_CONTACT_INSTRUCTIONS = TYPE_INSTRUCTIONS +
            "Enter the organization name: " +
            "Enter the address: " +
            "Enter the number: " +
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
    void optionsAreCaseInsensitive() {
        generateContactOptionInputs("eXIt\n");
        contactOption.start();
    }

    @Test
    void addPersonContactOutputCorrectInstructions() {
        generateContactOptionInputs(ADDING_PERSON_CONTACT_INPUT + "exit\n");
        contactOption.start();
        String expectedOutput = MAIN_INSTRUCTIONS + ADDING_PERSON_CONTACT_INSTRUCTIONS;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void addOrganizationContactOutputCorrectInstructions() {
        generateContactOptionInputs(ADDING_ORGANIZATION_CONTACT_INPUT + "exit\n");
        contactOption.start();
        String expectedOutput = MAIN_INSTRUCTIONS + ADDING_ORGANIZATION_CONTACT_INSTRUCTIONS;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void removeContactOutputCorrectInstructions() {
        generateContactOptionInputs(ADDING_PERSON_CONTACT_INPUT + ADDING_ORGANIZATION_CONTACT_INPUT +
                "remove\n1\nexit\n");
        contactOption.start();

        String expectedOutput = MAIN_INSTRUCTIONS +
                ADDING_PERSON_CONTACT_INSTRUCTIONS +
                ADDING_ORGANIZATION_CONTACT_INSTRUCTIONS +
                "1. John Doe\r\n" +
                "2. Pizza Store\r\n" +
                "Select a record: " +
                "The record removed!\r\n" +
                MAIN_INSTRUCTIONS;
        assertEquals(expectedOutput, outputStream.toString());
    }

    private void generateContactOptionInputs(String input) {
        contactOption = new ContactOption(new Scanner(input));
    }
}