package contacts.factory;

import contacts.contact.OrganizationContact;

import java.util.Scanner;

public class OrganizationContactFactory implements ContactFactory {
    private final Scanner scanner;

    public OrganizationContactFactory(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public OrganizationContact createContact() {
        String organizationName = scanner.nextLine();
        String address = scanner.nextLine();
        String phoneNumber = scanner.nextLine();
        return new OrganizationContact(organizationName, address, phoneNumber);
    }
}
