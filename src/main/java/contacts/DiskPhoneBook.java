package contacts;

import contacts.contact.Contact;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class DiskPhoneBook implements PhoneBook{
    private final File file;

    public DiskPhoneBook(File file) {
        this.file = file;
    }

    @Override
    public void addContact(Contact contact) {

    }

    @Override
    public List<Contact> getContacts() {
        return null;
    }

    @Override
    public List<String> getContactIndexInformation() {
        return null;
    }

    @Override
    public String getContactInformation(int index) {
        return null;
    }

    @Override
    public void editContactInformation(int i, Scanner scanner) {

    }

    @Override
    public void removeContact(int index) {

    }
}
