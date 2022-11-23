package contacts.contact;

public class PersonContact extends Contact {

    private final Person person;

    public PersonContact(Person person, String phoneNumber) {
        super(phoneNumber);
        this.person = person;
    }

    @Override
    public String getInfo() {
        return """
                Name: %s
                Surname: %s
                Birth date: %s
                Gender: %s
                Number: %s
                Time created: %s
                Time last edit: %s
                """.formatted(person.getName(),
                person.getSurname(),
                person.getBirthDate(),
                person.getGender(),
                getPhoneNumber(),
                getCreated(),
                getLastEdited());

    }

    @Override
    public void editInformation() {

    }

    @Override
    public String getName() {
        return null;
    }
}
