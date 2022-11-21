package contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactTest {

    private final Contact contact = new Contact("John", "Doe", "0523432");
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void getName() {
        String expected = "John";
        assertEquals(expected, contact.getName());
    }

    @Test
    void getSurname() {
        String expected = "Doe";
        assertEquals(expected, contact.getSurname());
    }

    @Test
    void getPhoneNumber() {
        String expected = "";
        assertEquals(expected, contact.getPhoneNumber());
    }

    @Test
    void testOutput() {
        String expected = "Wrong phone number!";
        assertEquals(expected, outputStreamCaptor.toString());
    }
}