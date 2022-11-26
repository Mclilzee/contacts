package contacts;

import contacts.phonebook.DiskPhoneBook;
import contacts.phonebook.MemoryPhoneBook;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ContactOption contactOption;
        if (args.length == 0) {
            contactOption = new ContactOption(scanner, new MemoryPhoneBook());
        } else {
            File file = new File("./" + args[0]);
            contactOption = new ContactOption(scanner, new DiskPhoneBook(file));
        }

        contactOption.start();
    }

}
