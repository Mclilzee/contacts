package contacts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ContactTest {

    Contact contact = new Contact("John", "Doe", "0523432");

    @Test
    void getName() {
        String expected = "John";
        assertEquals(expected, contact.getName());
    }

    @Test
    void getSurname() {
        String expected = "Doe";
        assertEquals(expected, contact.getSurname());
    }

    @Test
    void getPhoneNumber() {
        String expected = "0523432";
        assertEquals(expected, contact.getPhoneNumber());
    }
}