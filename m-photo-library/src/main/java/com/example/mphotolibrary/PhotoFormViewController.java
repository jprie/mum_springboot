package com.example.mphotolibrary;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.mphotolibrary.model.Photo;
import com.example.mphotolibrary.model.Photographer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;

public class PhotoFormViewController extends BaseController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextArea commentTextArea;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button fileChooserButton;

    @FXML
    private TextField imagePathTextField;

    @FXML
    private ImageView imageView;

    @FXML
    private ChoiceBox<Photographer> photographerChoiceBox;

    @FXML
    private Button resetButton;

    @FXML
    private TextField titleTextField;
    @FXML
    private Button editPhotographerButton;

    @FXML
    void onAddButtonPressed(ActionEvent event) {

//        Optional<Photo> photo = photoFromForm();
//        if (photo.isPresent()) {
//            model.photos.add(photo.get());
//        }

        photoFromForm().ifPresent(photo -> model.photos.add(photo));
    }




//TODO: Optional
    private Optional<Photo> photoFromForm() {
        String title = titleTextField.getText();
        Photographer photographer = photographerChoiceBox.getValue();
        LocalDate date = datePicker.getValue();
        String imagePath = imagePathTextField.getText();
        String comment = commentTextArea.getText();

        if (isValidInput(title, photographer, date, imagePath, comment)) {
            return Optional.of(new Photo(title, photographer, date, imagePath, comment));


        } else {
            System.err.println("No photo added: invalid input");
            return Optional.empty();
        }
    }

    private boolean isValidInput(String title, Photographer photographer, LocalDate date, String imagePath, String comment) {
        return title != null && !title.isEmpty() && photographer != null &&
                date != null && imagePath != null && !imagePath.isEmpty();
    }

    @FXML
    void onFileChooserButtonPressed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/Users/johannespriebsch/CloudStation/Geschäftlich/JavaKurse/Murad - Applikationsentwickler/Resources/PhotoResources/"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(currentWindow());

        if (file != null) {

            String path = file.getAbsolutePath();
            System.out.println("Image file: " + path);

            imagePathTextField.setText(path);
            loadImageFromPath(path);
        }
    }

    private void loadImageFromPath(String path) {

        try (InputStream inputStream = Files.newInputStream(Path.of(path))) {
            Image image = new Image(inputStream);
            imageView.setImage(image);
        } catch (IOException e) {
            System.err.println("Image " + path + " could not be loaded");
//            imageView.setImage(null); // brachiale Variante oder 'quick fix'
        }
    }

    private Window currentWindow() {
        return fileChooserButton.getScene().getWindow();
    }

    @FXML
    void onResetButtonPressed(ActionEvent event) {

        resetForm();
    }

    private void resetForm() {
        titleTextField.clear();
        imagePathTextField.clear();
        commentTextArea.clear();
        datePicker.setValue(null);
        photographerChoiceBox.getSelectionModel().clearSelection();
        imageView.setImage(null);
    }

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'photo-form-view.fxml'.";
        assert commentTextArea != null : "fx:id=\"commentTextArea\" was not injected: check your FXML file 'photo-form-view.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'photo-form-view.fxml'.";
        assert fileChooserButton != null : "fx:id=\"fileChooserButton\" was not injected: check your FXML file 'photo-form-view.fxml'.";
        assert imagePathTextField != null : "fx:id=\"filePathTextField\" was not injected: check your FXML file 'photo-form-view.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'photo-form-view.fxml'.";
        assert photographerChoiceBox != null : "fx:id=\"photographerChoiceBox\" was not injected: check your FXML file 'photo-form-view.fxml'.";
        assert resetButton != null : "fx:id=\"resetButton\" was not injected: check your FXML file 'photo-form-view.fxml'.";
        assert titleTextField != null : "fx:id=\"titleTextField\" was not injected: check your FXML file 'photo-form-view.fxml'.";

        photographerChoiceBox.setItems(model.photographers);

        datePicker.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            @Override
            public String toString(LocalDate localDate) {
                return localDate != null ? localDate.format(formatter) : null;
            }

            @Override
            public LocalDate fromString(String localDateString) {
                try {
                    LocalDate localDate = LocalDate.parse(localDateString, formatter);
                    return localDate;
                } catch (DateTimeParseException e) {
                    System.err.println("Could not parse date string: '" + localDateString + "'");
                }
                return null;
            }
        });

        model.selectedPhotoProperty().addListener(new ChangeListener<Photo>() {
            @Override
            public void changed(ObservableValue<? extends Photo> observableValue, Photo oldPhoto, Photo newPhoto) {

                if (newPhoto != null) {
                    resetAndFillForm(newPhoto);
                }
            }
        });

        model.selectedPhotographerProperty().bind(photographerChoiceBox.valueProperty());
    }

    private void resetAndFillForm(Photo newPhoto) {

        // reset form before fill
        resetForm();

        titleTextField.setText(newPhoto.getTitle());
        photographerChoiceBox.setValue(newPhoto.getPhotographer());
        datePicker.setValue(newPhoto.getDate());
        imagePathTextField.setText(newPhoto.getFilePath());
        commentTextArea.setText(newPhoto.getComment());

        loadImageFromPath(newPhoto.getFilePath());
    }

    @FXML
    private void onUpdateButtonPressed(ActionEvent actionEvent) {

        // wurde ein Photo ausgewählt
        Photo selectedPhoto = model.getSelectedPhoto();
        if (selectedPhoto != null) {

            photoFromForm().ifPresent(updatedPhoto -> {
                int index = model.photos.indexOf(selectedPhoto);
                model.photos.set(index, updatedPhoto);
            });
        }
    }

    @FXML
    private void onEditPhotographerButtonPressed(ActionEvent actionEvent) {

        // Photographer form view in neuem Fenster (Stage) öffnen
        try {
            editPhotographerButton.setDisable(true);
            PhotoLibraryApplication.loadFXMLViewInStageAndWait("photographer-form-view.fxml", new Stage());
            // dieser Code wird erst ausgeführt, sobald loadFXML... zurückkommt
            editPhotographerButton.setDisable(false);

        } catch (IOException e) {
            System.err.println("FXML view not found");
        }

    }
}