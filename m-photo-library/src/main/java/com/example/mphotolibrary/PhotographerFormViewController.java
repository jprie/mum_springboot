package com.example.mphotolibrary;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.mphotolibrary.model.Photo;
import com.example.mphotolibrary.model.Photographer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PhotographerFormViewController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button updateButton;
    @FXML
    private Label infoLabel;

    @FXML
    void onAddButtonPressed(ActionEvent event) {

        photographerFromForm().ifPresent(
                photographer -> {
                    if (!model.photographers.contains(photographer)) {
                        model.photographers.add(photographer);
                        infoLabel.setText("Hinzugefügt");
                    }
                }
        );
        // Idee: Überprüfe ob Photographer bereits vorhanden
        // model.photographers.contains()

    }

    private Optional<Photographer> photographerFromForm() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();

        if (isValid(firstName, lastName)) {
            return Optional.of(new Photographer(firstName, lastName));
        } else {
            System.err.println("Form data not correct");
            return Optional.empty();
        }
    }

    private boolean isValid(String firstName, String lastName) {
        return firstName != null && !firstName.isEmpty() &&
                lastName != null && !lastName.isEmpty();
    }

    @FXML
    void onCloseButtonPressed(ActionEvent event) {

        closeCurrentStage();
    }

    private void closeCurrentStage() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onDeleteButtonPressed(ActionEvent event) {

        // Lösche den Photographer wo firstName und lastName übereinstimmen
        Photographer selectedPhotographer = model.getSelectedPhotographer();
        if (selectedPhotographer != null) {
            model.photographers.remove(selectedPhotographer);
            resetForm();
            infoLabel.setText("Entfernt");
        }

    }

    private void resetForm() {
        firstNameTextField.clear();
        lastNameTextField.clear();
    }

    @FXML
    void onUpdateButtonPressed(ActionEvent event) {

        Photographer selectedPhotographer = model.getSelectedPhotographer();
        photographerFromForm().ifPresent(
                photographer -> {
                    int index = model.photographers.indexOf(selectedPhotographer);
                    model.photographers.set(index, photographer);
                    infoLabel.setText("Aktualisiert");
                }
        );
    }

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'photographer-form-view.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'photographer-form-view.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'photographer-form-view.fxml'.";
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'photographer-form-view.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'photographer-form-view.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'photographer-form-view.fxml'.";

        Photographer selectedPhotographer = model.getSelectedPhotographer();
        if (selectedPhotographer != null) {
            firstNameTextField.setText(selectedPhotographer.getFirstName());
            lastNameTextField.setText(selectedPhotographer.getLastName());
        }

        model.selectedPhotographerProperty().addListener(new ChangeListener<Photographer>() {
            @Override
            public void changed(ObservableValue<? extends Photographer> observableValue,
                                Photographer photographer,
                                Photographer newPhotographer) {

                if (newPhotographer != null) {
                    firstNameTextField.setText(newPhotographer.getFirstName());
                    lastNameTextField.setText(newPhotographer.getLastName());
                }
            }
        });
    }

}
