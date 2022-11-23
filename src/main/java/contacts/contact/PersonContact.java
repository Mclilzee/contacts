package contacts.contact;

import java.util.Scanner;

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
                super.getPhoneNumber(),
                super.getCreatedDateTime(),
                super.getLastEditedDateTime());

    }

    @Override
    public String getFullName() {
        return person.getName() + " " + person.getSurname();
    }

    public String getName() {
        return person.getName();
    }

    public String getSurname() {
        return person.getSurname();
    }

    public String getBirthDate() {
        return person.getBirthDate();
    }

    public String getGender() {
        return person.getGender();
    }

    @Override
    public void editInformation(Scanner scanner) {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        switch (scanner.nextLine().toLowerCase()) {
            case "name" -> editName(scanner);
            case "surname" -> editSurname(scanner);
            case "birth" -> editBirthDate(scanner);
            case "gender" -> editGender(scanner);
            case "number" -> editNumber(scanner);
        }

    }

    private void editNumber(Scanner scanner) {
        System.out.print("Enter number: ");
        setPhoneNumber(scanner.nextLine());
    }

    private void editGender(Scanner scanner) {
        System.out.print("Enter gender: ");
        person.setGender(scanner.nextLine());
    }

    private void editBirthDate(Scanner scanner) {
        System.out.print("Enter date: ");
        person.setBirthDate(scanner.nextLine());
    }

    private void editSurname(Scanner scanner) {
        System.out.print("Enter surname: ");
        person.setSurname(scanner.nextLine());
    }

    private void editName(Scanner scanner) {
        System.out.print("Enter name: ");
        person.setName(scanner.nextLine());
    }
}
