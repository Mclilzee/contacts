package contacts;

import contacts.options.ContactOption;

import java.util.Scanner;

public class ContactsApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ContactOption contactOption = new ContactOption(scanner);

    public static void main(String[] args) {
        contactOption.start();
    }

}
