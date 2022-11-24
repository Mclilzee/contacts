package contacts.factory;

import contacts.contact.PersonContact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PersonContactFactoryTest {
    private PersonContact contact;
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void setup () {
        outputStream.reset();
    }

    @Test
    void outputCorrectInstructions() {
        contact = getContactFromInput("John\nDoe\n1992-1-1\nM\n12345\n");
        String expectedOutput = "Enter the name: Enter the surname: Enter the birth date: Enter the gender: Enter the number: ";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void contactHasCorrectName() {
        contact = getContactFromInput("John\nDoe\n1992-1-1\nM\n+0 (123) 123-1412-999");
        String expectedName = "John";
        assertEquals(expectedName, contact.getName());

        contact = getContactFromInput("Emad\nDoblos\n1992-10-2\nF\n1234\n");
        String expectedNewContactName = "Emad";
        assertEquals(expectedNewContactName, contact.getName());
    }
    @Test
    void contactHasCorrectSurname() {
        contact = getContactFromInput("John\nDoe\n1992-1-1\nM\n+0 (123) 123-1412-999");
        String expectedSurname = "Doe";
        assertEquals(expectedSurname, contact.getSurname());

        contact = getContactFromInput("Emad\nSheblos\n2002-1-1\nM\n12345\n");
        String expectedNewContactSurname = "Sheblos";
        assertEquals(expectedNewContactSurname, contact.getSurname());

    }
    @Test
    void contactHasCorrectBirthDate() {
        contact = getContactFromInput("John\nDoe\n1992-1-1\nM\n+0 (123) 123-1412-999");
        String expected = "1992-1-1";
        assertEquals(expected, contact.getBirthDate());

        contact = getContactFromInput("Mark\nAsmar\n1999-02-02\nF\n+0 (123123) 22-22");
        String expectedNewBirthDate = "1999-02-02";
        assertEquals(expectedNewBirthDate, contact.getBirthDate());
    }

    @Test
    void contactHasCorrectGender() {
        contact = getContactFromInput("John\nDoe\n1992-1-1\nM\n+0 (123) 123-1412-999");
        String expectedGender = "M";
        assertEquals(expectedGender, contact.getGender());

        contact = getContactFromInput("Ali\nArab\n2005-02-03\nF\n00000\n");
        String expectedNewGender = "F";
        assertEquals(expectedNewGender, contact.getGender());
    }

    @Test
    void contactHasCorrectPhoneNumber() {
        contact = getContactFromInput("John\nDoe\n1992-1-1\nM\n+0 (123) 123-1412-999");
        String expectedPhoneNumber = "+0 (123) 123-1412-999";
        assertEquals(expectedPhoneNumber, contact.getPhoneNumber());

        contact = getContactFromInput("Doblos\nShenanigen\n2002-10-10\nF\n111111");
        String expectedNewPhoneNumber = "111111";
        assertEquals(expectedNewPhoneNumber, contact.getPhoneNumber());

    }

    private PersonContact getContactFromInput(String input) {
        PersonContactFactory factory = new PersonContactFactory(new Scanner(input));
        return factory.createContact();
    }
}