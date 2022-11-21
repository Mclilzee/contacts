package contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getName() {
        Contact contact = new Contact("John", "Doe", "+0 (123) 456-789-ABcd");
        String expected = "John";
        assertEquals(expected, contact.getName());
    }

    @Test
    void getSurname() {
        Contact contact = new Contact("John", "Doe", "+0 (123) 456-789-ABcd");
        String expected = "Doe";
        assertEquals(expected, contact.getSurname());
    }

    @Test
    void correctPhoneNumber() {
        Contact contact = new Contact("John", "Doe", "+0 (123) 456-789-ABcd");
        String expected = "+0 (123) 456-789-ABcd";
        assertEquals(expected, contact.getPhoneNumber());
    }

    @Test
    void wrongPhoneNumber() {
        Contact contact = new Contact("John", "Doe", "+0(123)1234123");
        String expected = "";
        assertEquals(expected, contact.getPhoneNumber());
    }

    @Test
    void testOutput() {
        Contact contact = new Contact("John", "Doe", "+0(123)1234123");
        String expected = "Wrong phone number!\r\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }
}