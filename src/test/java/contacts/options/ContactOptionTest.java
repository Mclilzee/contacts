package contacts.options;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactOptionTest {
    private final String PRINT_INSTRUCTIONS = "Enter action (add, remove, edit, count, list, exit): ";

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void exitOnExitCommand() {
        Scanner scanner = new Scanner("exit");
        ContactOption contactOption = new ContactOption(scanner);
        contactOption.start();
    }

    @Test
    void printCorrectInstructions() {
       Scanner scanner = new Scanner("exit");
       ContactOption contactOption = new ContactOption(scanner);
       contactOption.start();
       assertEquals(PRINT_INSTRUCTIONS, outputStream.toString());
    }

    @Test
    void printCorrectRecordCountInformation() {
        Scanner scanner = new Scanner("count\nexit");
        ContactOption contactOption = new ContactOption(scanner);
        contactOption.start();
        String expected = PRINT_INSTRUCTIONS + "The Phone Book has 0 records.\r\n" + PRINT_INSTRUCTIONS;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void addNewContactOption() {
        Scanner scanner = new Scanner("add\nJohn\nSmith\n+0 (123) 456-789-ABcd\ncount\nexit");
        ContactOption contactOption = new ContactOption(scanner);
        contactOption.start();

        String expected = PRINT_INSTRUCTIONS +
                "Enter the name: " +
                "Enter the surname: " +
                "Enter the number: " +
                "The record added.\r\n" +
                PRINT_INSTRUCTIONS +
                "The Phone Book has 1 records.\r\n" + PRINT_INSTRUCTIONS;
        assertEquals(expected, outputStream.toString());
    }
}