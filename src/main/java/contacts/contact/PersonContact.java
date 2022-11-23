package contacts.contact;

import contacts.util.InputUtil;

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
    public String getName() {
        return person.getName() + " " + person.getSurname();
    }

    @Override
    public void editInformation() {
        switch (InputUtil.getInput("Select a field (name, surname, birth, gender, number): ").toLowerCase()) {
            case "name" -> editName();
            case "surname" -> editSurname();
            case "birth" -> editBirthDate();
            case "gender" -> editGender();
            case "number" -> editNumber();
        }

    }

    private void editNumber() {
    }

    private void editGender() {
    }

    private void editBirthDate() {
    }

    private void editSurname() {
    }

    private void editName() {
    }
}
