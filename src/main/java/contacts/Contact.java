package contacts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Contact {

    private String name;
    private String surname;
    private String phoneNumber;
}
