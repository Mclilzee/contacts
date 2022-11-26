package contacts.utils;

import contacts.contact.Contact;

import java.util.List;
import java.util.stream.IntStream;

public class ContactPrinter {

    public static void printIndexList(List<Contact> contacts) {
        IntStream.range(0, contacts.size())
                .mapToObj(index -> (index + 1) + ". " + contacts.get(index).getFullName())
                .forEach(System.out::println);
    }
}
