package contacts;

import contacts.contact.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person = new Person("John", "Doe", "M", "1992-2-3");

    @Test
    void returnCorrectName() {
        String expected = "John";
        assertEquals(expected, person.getName());
    }

    @Test
    void returnCorrectSurname() {
        String expected = "Doe";
        assertEquals(expected, person.getSurname());
    }

    @Test
    void returnCorrectMaleGender() {
        String expected = "M";
        assertEquals(expected, person.getGender());
    }

    @ParameterizedTest
    @ValueSource(strings = {"f", "F", "M", "m"})
    void setCorrectGender(String gender) {
        String expected = gender.toUpperCase();
        person = new Person("John", "Doe", gender, "12345");
        assertEquals(expected, person.getGender());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"n", "s", "male", "female", ""})
    void setWrongGender(String gender) {
        person = new Person("John", "Doe", gender, "1993-1-1");
        String expected = "[no gender]";
        assertEquals(expected, person.getGender());
    }

    @Test
    void returnCorrectBirthdate() {
        String expected = "1992-2-3";
        assertEquals(expected, person.getBirthDate());
    }
}