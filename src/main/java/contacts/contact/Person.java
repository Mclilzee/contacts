package contacts.contact;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Person {
    private String name;
    private String surname;
    private String gender;
    private String birthDate;

    public Person(String name, String surname, String gender, String birthDate) {
        this.name = name;
        this.surname = surname;
        setGender(gender);
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        if (gender == null || !gender.matches("(?i)[MF]")) {
            this.gender = "[no gender]";
            System.out.println("Bad gender!");
        } else {
            this.gender = gender.toUpperCase();
        }
    }
}