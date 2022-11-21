package contacts;

import contacts.utils.StringUtils;
import lombok.Getter;

@Getter
public class Contact {

    private final String name;
    private final String surname;
    private final String phoneNumber;

    public Contact(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;

        if (StringUtils.isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
            System.out.println("Wrong phone number!");
        }
    }
}
