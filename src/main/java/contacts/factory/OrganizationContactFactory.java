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
        System.out.print("Enter the organization name: ");
        String organizationName = scanner.nextLine();

        System.out.print("Enter the address: ");
        String address = scanner.nextLine();

        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();
        return new OrganizationContact(organizationName, address, phoneNumber);
    }
}
