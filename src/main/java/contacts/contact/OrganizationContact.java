package contacts.contact;

import java.util.Scanner;

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
        return """
                Organization name: %s
                Address: %s
                Number: %s
                Time created: %s
                Time last edit: %s
                """.formatted(organizationName, address, getPhoneNumber(), getCreatedDateTime(), getLastEditedDateTime());
    }

    @Override
    public void editInformation(Scanner scanner) {
        System.out.print("Select a field (address, number): ");
        switch (scanner.nextLine().toLowerCase()) {
            case "address" -> editAddress(scanner);
            case "number" -> editPhoneNumber(scanner);
        }
    }

    private void editPhoneNumber(Scanner scanner) {
    }

    private void editAddress(Scanner scanner) {
        System.out.print("Enter address: ");
        address = scanner.nextLine();
    }

    @Override
    public String getFullName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }
}
