package contacts;

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
        this.gender = gender;
        this.birthDate = birthDate;
    }
}