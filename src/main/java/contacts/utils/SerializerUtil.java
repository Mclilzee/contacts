package contacts.utils;

import contacts.contact.Contact;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class SerializerUtil {

    public static void writeContacts(List<Contact> contacts, File file) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {

            objectOutputStream.writeObject(contacts);
        } catch (IOException ignored) {
        }
    }

    public static List<Contact> readContacts(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            return (List<Contact>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return List.of();
        }
    }
}
