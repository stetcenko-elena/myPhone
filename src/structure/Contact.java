package structure;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Contact {
    private String firstName;
    private String lastName;
    private TypeNumber typeNumber;
    private String number;
    private Group group;
    private String email;
    private String organization;
    private LocalDate birthday;

    public Contact(String firstName, String lastName, String typeNumber, String number, String group, String email, String organization, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeNumber = TypeNumber.valueOf(typeNumber);
        this.number = number;
        this.group = Group.valueOf(group);
        this.email = email;
        this.organization = organization;
        this.birthday = birthday;
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact() {
    }

    public StringProperty firstNameProperty() {
        return new SimpleStringProperty(firstName);
    }

    public StringProperty lastNameProperty() {
        return new SimpleStringProperty(lastName);
    }

    public StringProperty typeNumberProperty() {
        return new SimpleStringProperty(typeNumber.toString());
    }

    public StringProperty numberProperty() {
        return new SimpleStringProperty(number);
    }

    public StringProperty groupProperty() {
        return new SimpleStringProperty(group.toString());
    }

    public StringProperty emailProperty() {
        return new SimpleStringProperty(email);
    }

    public StringProperty organizationProperty() {
        return new SimpleStringProperty(organization);
    }

    public StringProperty birthdayProperty() {
        return new SimpleStringProperty(birthday.toString());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypeNumber() {
        try {
            return typeNumber.name();
        } catch (Exception e) {
            return "";
        }
    }

    public void setTypeNumber(TypeNumber typeNumber) {
        this.typeNumber = typeNumber;
    }

    public String getGroup() {
        try {
            return group.name();
        } catch (Exception e) {
            return "";
        }
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return LocalDate.now().getYear() - this.getBirthday().getYear();
    }
}
