package contacts.contact;

import lombok.Getter;
import lombok.Setter;

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
    public void editInformation() {

    }

    @Override
    public String getName() {
        return null;
    }
}
