package sample;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.util.DateUtil;
import structure.Contact;


public class PersonOverviewController {
    @FXML
    private TableView<Contact> personTable;
    @FXML
    private TableColumn<Contact, String> firstNameColumn;
    @FXML
    private TableColumn<Contact, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label typeNumberLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label groupLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label organizationLabel;
    @FXML
    private Label birthdayLabel;

    private Main main;

    public PersonOverviewController() {
    }

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        showPersonDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMain(Main main) {
        this.main = main;
        personTable.setItems(main.getPersonData());
    }

    private void showPersonDetails(Contact contact) {
        if (contact != null) {
            firstNameLabel.setText(contact.getFirstName());
            lastNameLabel.setText(contact.getLastName());
            typeNumberLabel.setText(contact.getTypeNumber());
            numberLabel.setText(contact.getNumber());
            groupLabel.setText(contact.getGroup());
            emailLabel.setText(contact.getEmail());
            organizationLabel.setText(contact.getOrganization());
            birthdayLabel.setText(DateUtil.format(contact.getBirthday()));

        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            typeNumberLabel.setText("");
            numberLabel.setText("");
            groupLabel.setText("");
            emailLabel.setText("");
            organizationLabel.setText("");
            birthdayLabel.setText("");
        }
    }


    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson() {
        Contact tempPerson = new Contact();
        boolean okClicked = main.showPersonEditDialog(tempPerson);
        if (okClicked) {
            main.getPersonData().add(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson() {
        Contact selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    public void saveAndClose() {
        Platform.exit();
    }

    @FXML
    private void handleSearchAge() {
        boolean okClicked = main.showSearchContactsByAge();
    }

    @FXML
    private void handleSearchNumber() {
        boolean okClicked = main.showSearchContactsByPhoneNumber();
    }

    @FXML
    private void handleSearchName() {
        boolean okClicked = main.showSearchContactsByName();
    }
}
