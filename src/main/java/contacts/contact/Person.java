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
        initGender(gender);
        this.birthDate = birthDate;
    }

    public void initGender(String gender) {
        if (gender == null || !gender.matches("(?i)[MF]")) {
            this.gender = "[no gender]";
            System.out.println("Bad gender!");
        } else {
            this.gender = gender.toUpperCase();
        }
    }

    public void setGender(String gender) {
        if (gender == null ) {
            gender = "";
        }

        if (!gender.matches("(?i)[MF]")) {
            System.out.println("Bad gender!");
        }

        this.gender = gender.toUpperCase();
    }
}