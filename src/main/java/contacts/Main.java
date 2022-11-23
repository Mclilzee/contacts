package contacts;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ContactOption contactOption = new ContactOption(scanner);

    public static void main(String[] args) {
        contactOption.start();
    }

}
