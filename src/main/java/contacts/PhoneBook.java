package contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class PhoneBook {

    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException();
        }

        contacts.add(contact);
    }

    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    public List<String> getRecordsInformation() {
        return IntStream.range(0, contacts.size())
                .mapToObj(index -> (index + 1) + ". " + contacts.get(index).getInfo())
                .toList();
    }

    public void editRecordInformation(int i) {
        contacts.get(i).editInformation();
    }
    public void setPhoneNumber(int i, String phoneNumber) {
        contacts.get(i).setPhoneNumber(phoneNumber);
    }

    public void removeRecord(int index) {
        contacts.remove(index);
    }
}
