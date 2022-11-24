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

class PersonContactTest {
    private final Person person = new Person("John", "Doe", "1991-2-12", "M");
    private PersonContact personContact = new PersonContact(person, "12345");
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
                Time created: %s
                Time last edit: %s
                """.formatted(mockDate.withNano(0), mockDate.withNano(0));
        assertEquals(expected, personContact.getInfo());
    }

    @Test
    void getCorrectFullName() {
        String expected = "John Doe";
        assertEquals(expected, personContact.getFullName());
    }

    @Test
    void getCorrectName() {
        String expected = "John";
        assertEquals(expected, personContact.getName());
    }

    @Test
    void getCorrectSurname() {
        String expected = "Doe";
        assertEquals(expected, personContact.getSurname());
    }

    @Test
    void getCorrectGender() {
        String expected = "M";
        assertEquals(expected, personContact.getGender());
    }

    @Test
    void getCorrectBirthDate() {
        String expected = "1991-2-12";
        assertEquals(expected, personContact.getBirthDate());
    }

    @Test
    void getCorrectPhoneNumber() {
        String expected = "12345";
        assertEquals(expected, personContact.getPhoneNumber());
    }

    @Test
    void getCreatedTime() {
        String expected = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        assertTrue(personContact.getCreatedDateTime().toString().matches(expected));
    }

    @Test
    void getLastEditTime() {
        String expected = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        assertTrue(personContact.getLastEditedDateTime().toString().matches(expected));
    }


    @Test
    void editsOptionsAreCaseInsensitive() {
        scanner = new Scanner("nAMe\nAli\n");
        personContact.editInformation(scanner);
        String expected = "Ali Doe";
        assertEquals(expected, personContact.getFullName());
    }

    @Test
    void editName() {
        scanner = new Scanner("name\nAli\n");
        personContact.editInformation(scanner);
        String expected = "Ali";
        assertEquals(expected, personContact.getName());

        String output = getFieldInstructions() + "Enter name: ";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void editSurname() {
        scanner = new Scanner("surname\nAli\n");
        personContact.editInformation(scanner);
        String expected = "Ali";
        assertEquals(expected, personContact.getSurname());

        String output = getFieldInstructions() + "Enter surname: ";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void editBirthDate() {
        scanner = new Scanner("birth\n1922-1-2\n");
        personContact.editInformation(scanner);
        String expected = "1922-1-2";
        assertEquals(expected, personContact.getBirthDate());

        String output = getFieldInstructions() + "Enter date: ";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void editGender() {
        scanner = new Scanner("gender\nf\n");
        personContact.editInformation(scanner);
        String expected = "F";
        assertEquals(expected, personContact.getGender());

        String output = getFieldInstructions() + "Enter gender: ";
        assertEquals(output, outputStream.toString());
    }

    @Test
    void editPhoneNumber() {
        scanner = new Scanner("number\n+12332\n");
        personContact.editInformation(scanner);
        String expected = "+12332";
        assertEquals(expected, personContact.getPhoneNumber());

        String output = getFieldInstructions() + "Enter number: ";
        assertEquals(output, outputStream.toString());
    }

    private String getFieldInstructions() {
        return "Select a field (name, surname, birth, gender, number): ";
    }
}