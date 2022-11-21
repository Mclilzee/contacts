package contacts;

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
    void commandsAreCaseInsensitive() {
        Scanner scanner = new Scanner("exIT");
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
}