package contacts;

import lombok.Getter;

@Getter
public class PersonContact extends Contact {

    private final Person person;

    public PersonContact(Person person, String phoneNumber) {
        super(phoneNumber);
        this.person = person;
    }

    @Override
    public String getInfo() {
        return null;
    }
}
