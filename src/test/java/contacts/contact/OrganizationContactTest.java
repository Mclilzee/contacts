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
    void getCreatedTime() {
        String expected = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        assertTrue(organizationContact.getCreatedDateTime().toString().matches(expected));
    }

    @Test
    void getLastEditTime() {
        String expected = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        assertTrue(organizationContact.getLastEditedDateTime().toString().matches(expected));
    }

    @Test
    void editInformationCaseInsensitive() {
        scanner = new Scanner("address\nBerlin Str. 20\n");
        organizationContact.editInformation(scanner);
        String expected = "Berlin Str. 20";
        assertEquals(expected, organizationContact.getAddress());

        String output = "Select a field (address, number): Enter address: ";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void editInformation() {
    }
}