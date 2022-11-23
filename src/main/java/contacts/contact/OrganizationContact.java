package contacts.contact;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter @Setter
public class OrganizationContact extends Contact {

    private String organizationName;
    private String address;

    public OrganizationContact(String organizationName, String address, String phoneNumber) {
        super(phoneNumber);
        this.organizationName = organizationName;
        this.address = address;
    }
    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void editInformation(Scanner scanner) {

    }

    @Override
    public String getFullName() {
        return null;
    }
}
