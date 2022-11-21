package contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactTest {

    private Contact contact = new Contact("John", "Doe", "+0 (123) 456-789-ABcd");
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
    void setName() {
        contact.setName("Mona");
        String expected = "Mona";
        assertEquals(expected, contact.getName());
    }

    @Test
    void getSurname() {
        String expected = "Doe";
        assertEquals(expected, contact.getSurname());
    }

    @Test
    void setSurname() {
        contact.setSurname("Dogman");
        String expected = "Dogman";
        assertEquals(expected, contact.getSurname());
    }

    @ParameterizedTest
    @DisplayName("Correct phone number set correctly")
    @MethodSource("provideCorrectPhoneNumbers")
    void correctPhoneNumber(String number) {
        contact = new Contact("John", "Doe", number);
        assertEquals(number, contact.getPhoneNumber());
    }

    @ParameterizedTest
    @DisplayName("Correct phone number outputs no message to the console")
    @MethodSource("provideCorrectPhoneNumbers")
    void correctPhoneNumberOutputNoMessage(String number) {
        contact = new Contact("John", "Doe", number);
        assertEquals("", outputStreamCaptor.toString());
    }

    @ParameterizedTest
    @DisplayName("Wrong number input will set field to correct value")
    @MethodSource("provideWrongNumbers")
    void wrongPhoneNumber(String number) {
        contact = new Contact("John", "Doe", number);
        String expected = "[no number]";
        assertEquals(expected, contact.getPhoneNumber());
    }


    @ParameterizedTest
    @DisplayName("Wrong number init will output the correct message")
    @MethodSource("provideWrongNumbers")
    void testInitOutput(String number) {
        outputStreamCaptor.reset();
        contact = new Contact("John", "Doe", number);
        assertEquals("Wrong number format!\r\n", outputStreamCaptor.toString());
    }

    @ParameterizedTest
    @DisplayName("Wrong number in setter will not change value")
    @MethodSource("provideWrongNumbers")
    void setterWrongPhoneNumber(String number) {
        outputStreamCaptor.reset();
        contact = new Contact("John", "Doe", "+0 (123) 456-789-ABcd");
        contact.setPhoneNumber(number);
        assertEquals("+0 (123) 456-789-ABcd", contact.getPhoneNumber());
    }

    @ParameterizedTest
    @DisplayName("Wrong number in setter will output correct message")
    @MethodSource("provideWrongNumbers")
    void setterWrongPhoneNumberOutput(String number) {
        outputStreamCaptor.reset();
        contact = new Contact("John", "Doe", "+0 (123) 456-789-ABcd");
        contact.setPhoneNumber(number);
        assertEquals("Wrong number format!\r\n", outputStreamCaptor.toString());
    }

    @Test
    void returnCorrectString() {
        outputStreamCaptor.reset();
        contact = new Contact("John", "Doe", "+0 (123) 456-789-ABcd");
        String expected = "John Doe, +0 (123) 456-789-ABcd";
        assertEquals(expected, contact.toString());
    }

    private static Stream<String> provideWrongNumbers() {
        return Stream.of(
                "+0(123)1234512)",
                null,
                "+(0) (123) 123-123-123",
                "+123 0 123-123-321",
                "+123 (123)1231-123-123"
        );
    }

    private static Stream<String> provideCorrectPhoneNumbers() {
        return Stream.of(
                "+0 (123) 456-789-ABcd",
                "(123) 234 345-456"
        );
    }
}