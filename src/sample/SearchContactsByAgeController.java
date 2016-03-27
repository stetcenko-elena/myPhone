package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.exceptions.ContactNotFoundException;
import storage.ContactManager;
import structure.Contact;

import java.io.IOException;

public class SearchContactsByAgeController {
    private Stage dialogStage;
    private ObservableList<Contact> baseResult = FXCollections.observableArrayList();
    @FXML
    private TextField age;
    @FXML
    private TextField ageFrom;
    @FXML
    private TextField ageTo;
    @FXML
    private TableView<Contact> tableContact;
    @FXML
    private TableColumn<Contact, String> firstNameColumn;
    @FXML
    private TableColumn<Contact, String> lastNameColumn;
    @FXML
    private TableColumn<Contact, String> typeNumberLabel;
    @FXML
    private TableColumn<Contact, String> numberLabel;
    @FXML
    private TableColumn<Contact, String> groupLabel;
    @FXML
    private TableColumn<Contact, String> emailLabel;
    @FXML
    private TableColumn<Contact, String> organizationLabel;
    @FXML
    private TableColumn<Contact, String> birthdayLabel;

    public SearchContactsByAgeController() {
    }

    @FXML
    private void initialize() throws IOException {
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        typeNumberLabel.setCellValueFactory(
                cellData -> cellData.getValue().typeNumberProperty());
        numberLabel.setCellValueFactory(
                cellData -> cellData.getValue().numberProperty());
        groupLabel.setCellValueFactory(
                cellData -> cellData.getValue().groupProperty());
        emailLabel.setCellValueFactory(
                cellData -> cellData.getValue().emailProperty());
        organizationLabel.setCellValueFactory(
                cellData -> cellData.getValue().organizationProperty());
        birthdayLabel.setCellValueFactory(
                cellData -> cellData.getValue().birthdayProperty());
    }

    public void searchContactsToAge() throws IOException {
        int value = Integer.valueOf(age.getText());
        ContactManager contactManager = new ContactManager();
        ObservableList observableList = null;
        try {
            observableList = contactManager.searchContactsByAge(value);
        } catch (ContactNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(e.getMessage());
            //alert.setContentText("");
            alert.show();
        }
        tableContact.setItems(observableList);
    }

    public void searchContactsByAgeRange() throws IOException {
        int from = Integer.valueOf(ageFrom.getText());
        int to = Integer.valueOf(ageTo.getText());
        ContactManager contactManager = new ContactManager();
        ObservableList observableList = null;
        try {
            observableList = contactManager.searchContactsByAgeRange(from, to);
        } catch (ContactNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(e.getMessage());
            //alert.setContentText("");
            alert.show();

        }
        tableContact.setItems(observableList);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
