package contacts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneBookTest {

    private final PhoneBook phoneBook = new PhoneBook();
    private final Contact firstContact = new Contact("John", "Doe", "+0 (123) 12345");
    private final Contact secondContact = new Contact("Mark", "Dobless", "+0 (123) 12345");
    private final Contact thirdContact = new Contact("Emad", "Seblos", "+0 (123) 12345");

    @Test
    void addContact() {
        phoneBook.addContact(firstContact);
        assertEquals(1, phoneBook.getContacts().size());
    }

    @Test
    void addMultipleContacts() {
        phoneBook.addContact(firstContact);
        phoneBook.addContact(secondContact);
        phoneBook.addContact(thirdContact);
        assertEquals(3, phoneBook.getContacts().size());
    }
}