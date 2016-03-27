package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.util.DateUtil;
import structure.Contact;
import structure.Group;
import structure.TypeNumber;

import java.time.format.DateTimeFormatter;

public class PersonEditDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ComboBox typeNumberComboBox;
    @FXML
    private TextField numberField;
    @FXML
    private ComboBox groupComboBox;
    @FXML
    private TextField emailField;
    @FXML
    private TextField organizationField;
    @FXML
    private DatePicker birthdayDatePicker;

    private Stage dialogStage;
    private Contact contact;
    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Contact contact) {
        this.contact = contact;

        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        typeNumberComboBox.setValue(contact.getTypeNumber().toString());
        numberField.setText(contact.getNumber());
        groupComboBox.setValue(contact.getGroup().toString());
        emailField.setText(contact.getEmail());
        organizationField.setText(contact.getOrganization());
        if (contact.getBirthday() != null) {
            birthdayDatePicker.setValue(DateUtil.parse(contact.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
        } else {
            birthdayDatePicker.setValue(null);
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            contact.setFirstName(firstNameField.getText());
            contact.setLastName(lastNameField.getText());
            contact.setTypeNumber(TypeNumber.valueOf(typeNumberComboBox.getValue().toString()));
            contact.setNumber(numberField.getText());
            contact.setGroup(Group.valueOf(groupComboBox.getValue().toString()));
            contact.setEmail(emailField.getText());
            contact.setOrganization(organizationField.getText());
            contact.setBirthday(DateUtil.parse(birthdayDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (typeNumberComboBox.getValue() == "") {
            errorMessage += "No valid type number!\n";
        }
        if (numberField.getText() == null || numberField.getText().length() == 0) {
            errorMessage += "No valid number!\n";
        }
        if (groupComboBox.getValue() == "") {
            errorMessage += "No valid group!\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        }
        if (organizationField.getText() == null || organizationField.getText().length() == 0) {
            errorMessage += "No valid organization!\n";
        }

        if (birthdayDatePicker.getValue() == null) {
            errorMessage += "No valid birthday!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void saveAndClose() {
        Platform.exit();
    }
}