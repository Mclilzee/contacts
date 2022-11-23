package contacts;

import contacts.contact.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactTest {

    private Contact contact = new ContactMock("+0 (123) 456-789-ABcd");
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @ParameterizedTest
    @DisplayName("Correct phone number set correctly")
    @MethodSource("provideCorrectPhoneNumbers")
    void correctPhoneNumber(String number) {
        contact = new ContactMock(number);
        assertEquals(number, contact.getPhoneNumber());
    }

    @ParameterizedTest
    @DisplayName("Correct phone number outputs no message to the console")
    @MethodSource("provideCorrectPhoneNumbers")
    void correctPhoneNumberOutputNoMessage(String number) {
        contact = new ContactMock(number);
        assertEquals("", outputStreamCaptor.toString());
    }

    @ParameterizedTest
    @DisplayName("Wrong number input will set field to correct value")
    @MethodSource("provideWrongNumbers")
    void wrongPhoneNumber(String number) {
        contact = new ContactMock(number);
        String expected = "[no number]";
        assertEquals(expected, contact.getPhoneNumber());
    }


    @ParameterizedTest
    @DisplayName("Wrong number init will output the correct message")
    @MethodSource("provideWrongNumbers")
    void testInitOutput(String number) {
        outputStreamCaptor.reset();
        contact = new ContactMock(number);
        assertEquals("Wrong number format!\r\n", outputStreamCaptor.toString());
    }

    @ParameterizedTest
    @DisplayName("Wrong number in setter will change value")
    @MethodSource("provideWrongNumbers")
    void setterWrongPhoneNumber(String number) {
        outputStreamCaptor.reset();
        contact = new ContactMock("+0 (123) 456-789-ABcd");
        contact.setPhoneNumber(number);
        assertEquals(number, contact.getPhoneNumber());
    }

    @ParameterizedTest
    @DisplayName("Wrong number in setter will output correct message")
    @MethodSource("provideWrongNumbers")
    void setterWrongPhoneNumberOutput(String number) {
        outputStreamCaptor.reset();
        contact = new ContactMock("+0 (123) 456-789-ABcd");
        contact.setPhoneNumber(number);
        assertEquals("Wrong number format!\r\n", outputStreamCaptor.toString());
    }

    private static Stream<String> provideWrongNumbers() {
        return Stream.of(
                "+0(123)1234512)",
                null,
                "+(0) (123) 123-123-123",
                "+123 0 123-123-321",
                "+123 (123)1231-123-123",
                "123 456 9"
        );
    }

    private static Stream<String> provideCorrectPhoneNumbers() {
        return Stream.of(
                "+0 (123) 456-789-ABcd",
                "(123) 234 345-456",
                "1234",
                "+12345",
                "123 abc",
                "(123)",
                "+1 11"
        );
    }

    static class ContactMock extends Contact {

        public ContactMock(String phoneNumber) {
            super(phoneNumber);
        }

        @Override
        public String getInfo() {
            return null;
        }

        @Override
        public void editInformation() {
        }
    }
}