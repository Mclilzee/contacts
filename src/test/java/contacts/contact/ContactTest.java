package contacts.contact;

import contacts.contact.Contact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class ContactTest {

    private Contact contact = new ContactMock("+0 (123) 456-789-ABcd");
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Scanner scanner = new Scanner("");

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @BeforeEach
    void setup() {
        outputStreamCaptor.reset();
    }

    @Test
    void getCorrectName() {
        String expected = "Mock Contact";
        assertEquals(expected, contact.getFullName());
    }

    @ParameterizedTest
    @DisplayName("Correct phone number set correctly")
    @MethodSource("provideCorrectPhoneNumbers")
    void getCorrectPhoneNumber(String number) {
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
        ContactMock contact = new ContactMock("+0 (123) 456-789-ABcd");
        contact.mockSetPhoneNumber(number);
        assertEquals(number, contact.getPhoneNumber());
    }

    @ParameterizedTest
    @DisplayName("Wrong number in setter will output correct message")
    @MethodSource("provideWrongNumbers")
    void setterWrongPhoneNumberOutput(String number) {
        outputStreamCaptor.reset();

        ContactMock contact = new ContactMock("+0 (123) 456-789-ABcd");
        contact.mockSetPhoneNumber(number);
        assertEquals("Wrong number format!\r\n", outputStreamCaptor.toString());
    }

    @Test
    void createdTimeSetCorrectly() {
        LocalDateTime beforeCreation = LocalDateTime.now().minusMinutes(1);
        contact = new ContactMock("123456");
        LocalDateTime afterCreation = LocalDateTime.now().plusMinutes(1);

        assertTrue(contact.getCreatedDateTime().isAfter(beforeCreation));
        assertTrue(contact.getCreatedDateTime().isBefore(afterCreation));
    }

    @Test
    void lastEditTimeSetCorrectlyOnCreation() {
        contact = new ContactMock("12345");
        assertEquals(contact.getCreatedDateTime(), contact.getLastEditedDateTime());
    }

    @Test
    void lastEditTimeUpdatedCorrectlyOnEdit() {
        contact = new ContactMock("123456");
        LocalDateTime mockTime = LocalDateTime.of(2000, 1, 1, 1, 1, 1, 20);
        LocalDateTime expected = LocalDateTime.of(2000, 1, 1, 1, 1, 1);

        try (MockedStatic<LocalDateTime> localDateTimeMock = mockStatic(LocalDateTime.class)) {
            localDateTimeMock.when(LocalDateTime::now).thenReturn(mockTime);
            contact.editContact(scanner);
            assertEquals(contact.getLastEditedDateTime(), expected);
        }
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
        protected void editInformation(Scanner scanner) {
        }

        @Override
        public String getFullName() {
            return "Mock Contact";
        }

        public void mockSetPhoneNumber(String phoneNumber) {
            setPhoneNumber(phoneNumber);
        }
    }
}