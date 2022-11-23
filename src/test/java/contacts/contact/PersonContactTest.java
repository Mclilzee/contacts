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
import static org.mockito.Mockito.mockStatic;

class PersonContactTest {
    private final Person person = new Person("John", "Doe", "M", "1991-2-12");
    private PersonContact personContact = new PersonContact(person, "123456");
    Scanner scanner = new Scanner("");
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
    void getInfoWithCorrectInformation() {
        LocalDateTime mockDate = LocalDateTime.of(2022, 5, 9, 5, 40, 59, 20);
        try (MockedStatic<LocalDateTime> localDateTimeMock = mockStatic(LocalDateTime.class)) {
            localDateTimeMock.when(LocalDateTime::now).thenReturn(mockDate);
            personContact = new PersonContact(person, "12345");
        }

        String expected = """
                Name: John
                Surname: Doe
                Birth date: 1991-2-12
                Gender: M
                Number: 12345
                Time created: 2022-05-09T05:40:59
                Time last edit: 2022-05-09T05:40:59
                """;
        assertEquals(expected, personContact.getInfo());
    }

    @Test
    void getName() {
        String expected = "John Doe";
        assertEquals(expected, personContact.getFullName());
    }

    @Test
    void editName() {
        scanner = new Scanner("name\nAli\n");
        personContact.editInformation(scanner);
        String expected = "Ali Doe";
        assertEquals(expected, personContact.getFullName());
    }
}