package storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.exceptions.ContactNotFoundException;
import sample.util.DateUtil;
import structure.Contact;
import structure.Group;
import structure.TypeNumber;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ContactManager {

    private Set<Contact> contacts;

    public ContactManager() throws IOException {
        this.contacts = new HashSet<>();
        load();
    }

    public void load() throws IOException {
        //try (Stream<String> stream = Files.lines(Paths.get("/resources/storage.txt"))) {
        try (Scanner scanner = new Scanner(Paths.get("resources/storage.txt").toAbsolutePath())) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" ");
                Contact contact = new Contact();
                contact.setFirstName(data[0]);
                contact.setLastName(data[1]);
                contact.setTypeNumber(TypeNumber.valueOf(data[2]));
                contact.setNumber(data[3]);
                contact.setGroup(Group.valueOf(data[4]));
                contact.setEmail(data[5]);
                contact.setOrganization(data[6]);
                contact.setBirthday(DateUtil.parse(data[7]));
                contacts.add(contact);
            }
        }
    }

    public void saveToFile(ObservableList<Contact> contacts) throws IOException {
        Files.deleteIfExists(Paths.get("resources/storage.txt").toAbsolutePath());
        Files.createFile(Paths.get("resources/storage.txt").toAbsolutePath());
        try (Scanner scanner = new Scanner(Paths.get("resources/storage.txt").toAbsolutePath())) {
            for (Contact contact : contacts) {
                String contactLine = contact.getFirstName() + " "
                        + contact.getLastName() + " "
                        + contact.getTypeNumber() + " "
                        + contact.getNumber() + " "
                        + contact.getGroup() + " "
                        + contact.getEmail() + " "
                        + contact.getOrganization() + " "
                        + DateUtil.format(contact.getBirthday()) + "\n";
                Files.write(
                        Paths.get("resources/storage.txt"),
                        contactLine.getBytes(),
                        StandardOpenOption.APPEND
                );
            }
        }
    }

    public Set<Contact> getAllContacts() {
        return contacts;
    }

    public ObservableList<Contact> searchContactsByFirstOrLastName(String name) throws ContactNotFoundException {
        ObservableList<Contact> listResult = FXCollections.observableArrayList();
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(name) || contact.getLastName().equals(name)) {
                listResult.add(contact);
            }
        }
        if (listResult.isEmpty()) {
            throw new ContactNotFoundException("No contacts with the correct first or last name " + name);
        }
        return listResult;
    }

    public ObservableList searchContactsByAnyPartOfName(String value) throws ContactNotFoundException {
        ObservableList<Contact> listResult = FXCollections.observableArrayList();
        for (Contact contact : contacts) {
            if (contact.getFirstName().contains(value) || contact.getLastName().contains(value)) {
                listResult.add(contact);
            }
        }
        if (listResult.isEmpty()) {
            throw new ContactNotFoundException("no contacts in the content of which has " + value);
        }
        return listResult;
    }

    public ObservableList<Contact> searchContactsByPhoneNumber(String phone) throws ContactNotFoundException {
        ObservableList<Contact> listResult = FXCollections.observableArrayList();
        for (Contact contact : contacts) {
            if (contact.getNumber().equals(phone)) {
                listResult.add(contact);
            }
        }
        if (listResult.isEmpty()) {
            throw new ContactNotFoundException("No contacts with the correct number phone " + phone);
        }
        return listResult;
    }


    public ObservableList searchContactsByAge(int age) throws ContactNotFoundException {
        ObservableList<Contact> listResult = FXCollections.observableArrayList();
        for (Contact contact : contacts) {
            if (contact.getAge() == age) {
                listResult.add(contact);
            }
        }
        if (listResult.isEmpty()) {
            throw new ContactNotFoundException("No contact with the appropriate age " + age);
        }
        // result.addAll(contacts);
        return listResult;
    }

    public ObservableList searchContactsByAgeRange(int ageFrom, int ageTo) throws ContactNotFoundException {
        ObservableList<Contact> listResult = FXCollections.observableArrayList();
        for (Contact contact : contacts) {
            if (contact.getAge() >= ageFrom && contact.getAge() <= ageTo) {
                listResult.add(contact);
            }
        }
        if (listResult.isEmpty()) {
            throw new ContactNotFoundException("No contacts in this age range from " + ageFrom + " to " + ageTo);
        }
        return listResult;
    }
}
