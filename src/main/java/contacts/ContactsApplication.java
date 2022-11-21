package contacts;

import contacts.options.MainOption;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApplication implements CommandLineRunner {

    private final MainOption mainOption;

    public ContactsApplication(MainOption mainOption) {
        this.mainOption = mainOption;
    }

    public static void main(String[] args) {
        SpringApplication.run(ContactsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainOption.start();
    }
}
