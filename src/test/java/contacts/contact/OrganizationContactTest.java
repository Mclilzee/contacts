package contacts.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

class OrganizationContactTest {

    private OrganizationContact organizationContact = new OrganizationContact("Pizza store", "Gnar Str. 11", "12345");

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
                """.formatted(mockTime, mockTime);
        assertEquals(expected, organizationContact.getInfo());
    }

    @Test
    void editInformation() {
    }

    @Test
    void getFullName() {
    }

    @Test
    void getOrganizationName() {
    }

    @Test
    void getAddress() {
    }
}