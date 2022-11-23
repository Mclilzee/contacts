package contacts.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
