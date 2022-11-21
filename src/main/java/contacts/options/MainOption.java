package contacts.options;

import java.util.Scanner;

public class MainOption {

    private final Scanner scanner;
    private final ContactOption contactOption;

    public MainOption(Scanner scanner, ContactOption contactOption) {
        this.scanner = scanner;
        this.contactOption = contactOption;
    }

    public void start() {
        contactOption.start();
    }
}
