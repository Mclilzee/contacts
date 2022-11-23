package contacts.contact;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class PersonContactTest {
    private final Person person = new Person("John", "Doe", "M", "1991-2-12");
    private PersonContact personContact = new PersonContact(person, "123456");


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

    void getInfoWithCorrectEditDate() {
        LocalDateTime mockDate = LocalDateTime.of(2022, 5, 9, 5, 40, 59, 20);
        LocalDateTime mockEditDate = LocalDateTime.of(2023, 5, 10, 12, 2, 5, 10);
        try (MockedStatic<LocalDateTime> localDateTimeMock = mockStatic(LocalDateTime.class)) {
            localDateTimeMock.when(LocalDateTime::now).thenReturn(mockDate);
            personContact = new PersonContact(person, "12345");
            localDateTimeMock.when(LocalDateTime::now).thenReturn(mockEditDate);
            personContact.setPhoneNumber("123");
        }

        String expected = """
                Name: John
                Surname: Doe
                Birth date: 1991-2-12
                Gender: M
                Number: 123
                Time created: 2022-05-09T05:40:59
                Time last edit: 2023-05-10T12:02:05
                """;
        assertEquals(expected, personContact.getInfo());
    }

    @Test
    void editInformation() {
    }

    @Test
    void getName() {
    }
}