package contacts.phonebook;

import contacts.contact.Contact;
import contacts.utils.SerializerUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiskPhoneBook implements PhoneBook {
    private final File file;

    public DiskPhoneBook(File file) {
        this.file = file;
    }

    @Override
    public void addContact(Contact contact) {
        List<Contact> contacts = new ArrayList<>(SerializerUtil.readContacts(file));
        contacts.add(contact);
        SerializerUtil.writeContacts(contacts, file);
    }

    @Override
    public List<Contact> getContacts() {
        return SerializerUtil.readContacts(file);
    }

    @Override
    public void editContactInformation(int index, Scanner scanner) {
        List<Contact> contacts = SerializerUtil.readContacts(file);
        contacts.get(index).editContact(scanner);
        SerializerUtil.writeContacts(contacts, file);
    }

    @Override
    public void removeContact(int index) {
        List<Contact> contacts = SerializerUtil.readContacts(file);
        contacts.remove(index);
        SerializerUtil.writeContacts(contacts, file);
    }
}
