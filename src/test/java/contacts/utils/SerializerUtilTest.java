package contacts.utils;

import contacts.contact.Contact;
import contacts.contact.OrganizationContact;
import contacts.contact.Person;
import contacts.contact.PersonContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerializerUtilTest {

    File file = new File("./src/test/java/contacts/utils/contacts.txt");
    List<Contact> contacts = List.of(
            new OrganizationContact("Pizza Store", "Wall str. 1", "1234"),
            new OrganizationContact("Game Store", "Gnarr str. 1", "222"),
            new PersonContact(new Person("John", "Doe", "1999-10-10", "M"), "12345")
    );

    @BeforeEach
    void setup() {
        file.delete();
    }

    @Test
    void createFileIfNotExists() {
        SerializerUtil.writeContacts(contacts, file);
        assertTrue(file.exists());
    }

    @Test
    void fileContainsCorrectInformation() {

    }

    @Test
    void fileContainsNoInformation() {

    }

    @Test
    void containCorrectObjects() {

    }
}
