package contacts.factory;

import contacts.contact.OrganizationContact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrganizationContactFactoryTest {
    private OrganizationContact contact;
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
    void outputCorrectInstructions() {
        contact = generateContactFromInput("Pizza Shop\nWall St. 1\n+0 (123) 456-789-9999\n");
        String expected = "Enter the organization name: Enter the address: Enter the number: ";
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void contactHasCorrectName() {
        contact = generateContactFromInput("Pizza Shop\nWall St. 1\n+0 (123) 456-789-9999\n");
        String expectedName = "Pizza Shop";
        assertEquals(expectedName, contact.getFullName());

        contact = generateContactFromInput("Game Store\nGnarworld Str. 100\n12345");
        String expectedNewContactName = "Game Store";
        assertEquals(expectedNewContactName, contact.getFullName());
    }

    @Test
    void contactHasCorrectAddress() {
        contact = generateContactFromInput("Pizza Shop\nWall St. 1\n+0 (123) 456-789-9999\n");
        String expectedAddress = "Wall St. 1";
        assertEquals(expectedAddress, contact.getAddress());

        contact = generateContactFromInput("Game store\nWorld Str. 01\n12345");
        String expectedNewContactAddress = "World Str. 01";
        assertEquals(expectedNewContactAddress, contact.getAddress());
    }

    @Test
    void contactHasCorrectPhoneNumber() {
        contact = generateContactFromInput("Pizza Shop\nWall St. 1\n+0 (123) 456-789-9999\n");
        String expectedPhoneNumber = "+0 (123) 456-789-9999";
        assertEquals(expectedPhoneNumber, contact.getPhoneNumber());

        contact = generateContactFromInput("Game store\nWorld Str. 01\n+0 1234 12345");
        String expectedNewPhoneNumber = "+0 1234 12345";
        assertEquals(expectedNewPhoneNumber, contact.getPhoneNumber());
    }

    private OrganizationContact generateContactFromInput(String input) {
       OrganizationContactFactory factory = new OrganizationContactFactory(new Scanner(input));
       return factory.createContact();
    }
}