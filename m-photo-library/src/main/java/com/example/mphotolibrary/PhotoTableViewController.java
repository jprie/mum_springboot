package com.example.mphotolibrary;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.mphotolibrary.model.Photo;
import com.example.mphotolibrary.model.Photographer;
import com.example.mphotolibrary.tablecell.DeleteButtonTableCell;
import com.example.mphotolibrary.tablecell.ThumbnailTableCell;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class PhotoTableViewController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Photo, String> commentColumn;

    @FXML
    private TableColumn<Photo, LocalDate> dateColumn;

    @FXML
    private TableColumn<Photo, Button> deleteButtonColumn;

    @FXML
    private TableColumn<Photo, String> imagePathColumn;

    @FXML
    private TableView<Photo> photoTableView;

    @FXML
    private TableColumn<Photo, Photographer> photographerColumn;

    @FXML
    private TableColumn<Photo, ImageView> thumbnailColumn;

    @FXML
    private TableColumn<Photo, String> titleColumn;

    @FXML
    void initialize() {
        assert commentColumn != null : "fx:id=\"commentColumn\" was not injected: check your FXML file 'photo-table-view.fxml'.";
        assert dateColumn != null : "fx:id=\"dateColumn\" was not injected: check your FXML file 'photo-table-view.fxml'.";
        assert deleteButtonColumn != null : "fx:id=\"deleteButtonColumn\" was not injected: check your FXML file 'photo-table-view.fxml'.";
        assert imagePathColumn != null : "fx:id=\"imagePathColumn\" was not injected: check your FXML file 'photo-table-view.fxml'.";
        assert photoTableView != null : "fx:id=\"photoTableView\" was not injected: check your FXML file 'photo-table-view.fxml'.";
        assert photographerColumn != null : "fx:id=\"photographerColumn\" was not injected: check your FXML file 'photo-table-view.fxml'.";
        assert thumbnailColumn != null : "fx:id=\"thumbnailColumn\" was not injected: check your FXML file 'photo-table-view.fxml'.";
        assert titleColumn != null : "fx:id=\"titleColumn\" was not injected: check your FXML file 'photo-table-view.fxml'.";

        photoTableView.setItems(model.photos);

        // Wie sollen Daten in der Spalte dargestellt werden
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        photographerColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPhotographer()));
        dateColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDate()));
        imagePathColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFilePath()));
        commentColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getComment()));

        // selbst definieren wie Daten in Zelle angezeigt werden sollen
        thumbnailColumn.setCellFactory(photoImageViewTableColumn ->
                new ThumbnailTableCell());

        deleteButtonColumn.setCellFactory(photoButtonTableColumn ->
                new DeleteButtonTableCell());

        photoTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Photo>() {
            @Override
            public void changed(ObservableValue<? extends Photo> observableValue, Photo oldPhoto, Photo newPhoto) {

                if (newPhoto != null) {
                    System.out.println(newPhoto);
                    model.setSelectedPhoto(newPhoto);
                }
            }
        });
    }
}








