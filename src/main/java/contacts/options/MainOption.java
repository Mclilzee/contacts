package contacts.options;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainOption {
    Scanner scanner;

    public MainOption(Scanner scanner) {
        this.scanner = scanner;
    }
    public void start() {
    }
}
