package contacts.utils;

import contacts.contact.Contact;

import java.util.List;
import java.util.stream.IntStream;

public class ContactPrinter {
    public static void printIndexList(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No records to show!");
            return;
        }

        IntStream.range(0, contacts.size())
                .mapToObj(index -> (index + 1) + ". " + contacts.get(index).getFullName())
                .forEach(System.out::println);
    }

    public static void printContactInfo(List<Contact> contacts, int i) {
        if (contacts.isEmpty()) {
            System.out.println("No records to show!");
        }

        if (i < 0 || i > contacts.size() - 1) {
            System.out.println("Incorrect index number");
            return;
        }

        System.out.println(contacts.get(i).getInfo());
    }
}
