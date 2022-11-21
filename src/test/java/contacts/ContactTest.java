package contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactTest {

    private final String WRONG_NUMBER_OUTPUT = "Wrong number format!\r\n";
    private final Contact contact = new Contact("John", "Doe", "+0 (123) 456-789-ABcd");
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
    void correctPhoneNumber() {
        String expected = "+0 (123) 456-789-ABcd";
        assertEquals(expected, contact.getPhoneNumber());
    }

    @Test
    void wrongPhoneNumber() {
        Contact contact = new Contact("John", "Doe", "+0(123)1234123");
        String expected = "[no number]";
        assertEquals(expected, contact.getPhoneNumber());
    }

    @Test
    void testInitOutput() {
        Contact contact = new Contact("John", "Doe", "+0(123)1234123");
        assertEquals(WRONG_NUMBER_OUTPUT, outputStreamCaptor.toString());
    }

    @Test
    void setterWrongPhoneNumberOutput() {
        contact.setPhoneNumber("+0(123)12345123");
        assertEquals(WRONG_NUMBER_OUTPUT, outputStreamCaptor.toString());
    }
}