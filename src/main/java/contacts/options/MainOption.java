package contacts.options;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
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
