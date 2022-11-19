package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApplication implements CommandLineRunner {

    private final CreateContacts createContacts;

    public ContactsApplication(CreateContacts createContacts) {
        this.createContacts = createContacts;
    }

    public static void main(String[] args) {
        SpringApplication.run(ContactsApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        createContacts.createContacts();
    }
}
