package contacts.phonebook;

class MemoryPhoneBookTest extends PhoneBookBaseTest {

    @Override
    protected PhoneBook createInstance() {
        return new MemoryPhoneBook();
    }
}