package contacts;

import java.util.Scanner;

public class ContactsApplication {

    private static final ContactOption contactOption = new ContactOption(new Scanner(System.in));

    public static void main(String[] args) {
        contactOption.start();
    }

}
