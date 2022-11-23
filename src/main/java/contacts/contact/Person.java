package contacts.contact;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@Getter @Setter
public class Person {
    private static final Pattern genderPattern = Pattern.compile("[MF]", Pattern.CASE_INSENSITIVE);

    private String name;
    private String surname;
    private String gender;
    private String birthDate;

    public Person(String name, String surname, String gender, String birthDate) {
        this.name = name;
        this.surname = surname;
        initGender(gender);
        setBirthDate(birthDate);
    }

    public void initGender(String gender) {
        if (gender == null || !genderPattern.matcher(gender).matches()) {
            this.gender = "[no data]";
            System.out.println("Bad gender!");
        } else {
            this.gender = gender.toUpperCase();
        }
    }

    public void setGender(String gender) {
        if (gender == null ) {
            gender = "";
        }

        if (!genderPattern.matcher(gender).matches()) {
            System.out.println("Bad gender!");
        }

        this.gender = gender.toUpperCase();
    }

    public void setBirthDate(String birthDate) {
       if (isNotValidBirthDate(birthDate)) {
           this.birthDate = "[no data]";
           System.out.println("Bad date!");
       } else {
           this.birthDate = birthDate;
       }
    }

    private boolean isNotValidBirthDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate date = LocalDate.parse(dateString, formatter);
            return date.isAfter(LocalDate.now()) || date.isBefore(LocalDate.of(1900, 1, 1));
        } catch (DateTimeParseException | NullPointerException ex) {
            return true;
        }
    }
}