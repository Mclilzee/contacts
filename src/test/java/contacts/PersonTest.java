package contacts;

import contacts.contact.Person;
import org.junit.jupiter.api.Test;

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
    void returnCorrectGender() {
        String expected = "M";
        assertEquals(expected, person.getGender());
    }

    @Test
    void returnCorrectBirthdate() {
        String expected = "1992-2-3";
        assertEquals(expected, person.getBirthDate());
    }

}