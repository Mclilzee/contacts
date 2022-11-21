package contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("provideWrongNumbers")
    void wrongPhoneNumber(String number) {
        Contact contact = new Contact("John", "Doe", number);
        String expected = "[no number]";
        assertEquals(expected, contact.getPhoneNumber());
    }


    @ParameterizedTest
    @MethodSource("provideWrongNumbers")
    void testInitOutput(String number) {
        Contact contact = new Contact("John", "Doe", number);
        assertEquals(WRONG_NUMBER_OUTPUT, outputStreamCaptor.toString());
    }

    @ParameterizedTest
    @MethodSource("provideWrongNumbers")
    void setterWrongPhoneNumberOutput(String number) {
        contact.setPhoneNumber(number);
        assertEquals(WRONG_NUMBER_OUTPUT, outputStreamCaptor.toString());
        assertEquals("+0 (123) 456-789-ABcd", contact.getPhoneNumber());
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
}