package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {

    private final Scanner scanner;

    private final List<Contact> contacts = new ArrayList<>();

    public PhoneBook(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addContact(Contact contact) {
        if (contact == null) {
            return;
        }

        contacts.add(contact);
        System.out.println("The record added.");
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
