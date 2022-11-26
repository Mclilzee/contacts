package contacts.contact;

import java.io.Serial;
import java.util.Scanner;

public class OrganizationContact extends Contact {

    @Serial
    private static final long serialVersionUID = 1;
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
    public String getFullName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public void editInformation(Scanner scanner) {
        System.out.print("Select a field (address, number): ");
        switch (scanner.nextLine().toLowerCase()) {
            case "address" -> editAddress(scanner);
            case "number" -> editPhoneNumber(scanner);
            case "name" -> editName(scanner);
        }
    }

    private void editPhoneNumber(Scanner scanner) {
        System.out.print("Enter number: ");
        setPhoneNumber(scanner.nextLine());
    }

    private void editAddress(Scanner scanner) {
        System.out.print("Enter address: ");
        address = scanner.nextLine();
    }

    private void editName(Scanner scanner) {
        System.out.print("Enter name: ");
        organizationName = scanner.nextLine();
    }
}
