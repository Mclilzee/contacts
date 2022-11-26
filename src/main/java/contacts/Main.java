package contacts;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static ContactOption contactOption;

    public static void main(String[] args) {
        if (args.length == 0) {
            contactOption = new ContactOption(scanner, new MemoryPhoneBook());
        } else {
            File file = new File(args[0]);
            contactOption = new ContactOption(scanner, new DiskPhoneBook(file));
        }

        contactOption.start();
    }

}
