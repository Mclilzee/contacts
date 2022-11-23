package contacts.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

class OrganizationContactTest {

    private OrganizationContact organizationContact = new OrganizationContact("Pizza store", "Gnar Str. 11", "12345");
    private Scanner scanner = new Scanner("");

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void setup() {
        outputStream.reset();
    }

    @Test
    void getInfo() {
        LocalDateTime mockTime = LocalDateTime.of(1999, 10, 10, 10, 10, 10, 10);
        try (MockedStatic<LocalDateTime> localDateTimeMock = mockStatic(LocalDateTime.class)) {
            localDateTimeMock.when(LocalDateTime::now).thenReturn(mockTime);
            organizationContact = new OrganizationContact("Pizza store", "Gnar Str. 11", "12345");
        }

        String expected = """
                Organization name: Pizza store
                Address: Gnar Str. 11
                Number: 12345
                Time created: %s
                Time last edit: %s
                """.formatted(mockTime.withNano(0), mockTime.withNano(0));
        assertEquals(expected, organizationContact.getInfo());
    }

    @Test
    void getFullName() {
        String expected = "Pizza store";
        assertEquals(expected, organizationContact.getFullName());
    }

    @Test
    void getAddress() {
        String expected = "Gnar Str. 11";
        assertEquals(expected, organizationContact.getAddress());
    }

    @Test
    void getPhoneNumber() {
        String expected = "12345";
        assertEquals(expected, organizationContact.getPhoneNumber());
    }

    @Test
    void emptyInputDoesNothing() {
        scanner = new Scanner("\n");
        organizationContact.editInformation(scanner);

        String output = getSelectFieldMessage();
        assertEquals(output, outputStream.toString());
    }

    @Test
    void editInformationCaseInsensitive() {
        scanner = new Scanner("adDREss\nBerlin Str. 20\n");
        organizationContact.editInformation(scanner);
        String expected = "Berlin Str. 20";
        assertEquals(expected, organizationContact.getAddress());

        String output = getSelectFieldMessage() + "Enter address: ";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void editAddress() {
        scanner = new Scanner("address\nBerlin Str. 20\n");
        organizationContact.editInformation(scanner);
        String expected = "Berlin Str. 20";
        assertEquals(expected, organizationContact.getAddress());

        String output = getSelectFieldMessage() + "Enter address: ";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void editPhoneNumber() {
        scanner = new Scanner("number\n123\n");
        organizationContact.editInformation(scanner);
        String expected = "123";
        assertEquals(expected, organizationContact.getPhoneNumber());

        String output = getSelectFieldMessage() + "Enter number: ";
        assertEquals(output, outputStream.toString());
    }

    private static String getSelectFieldMessage() {
        return "Select a field (address, number): ";
    }
}