package com.example.mphotolibrary.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhotoLibraryModel {

    static List<Photographer> defaultPhotographers () {
        return new ArrayList<>(
                List.of(
                  new Photographer("Johannes", "Priebsch"),
                  new Photographer("Dirk", "Bach")
            )
        );
    }
    static List<Photo> defaultPhotos () {
        return new ArrayList<Photo>(
                List.of(
                        new Photo("San Sebastián", defaultPhotographers().get(0),
                                LocalDate.of(2018,7,3),
                                "/Users/johannespriebsch/CloudStation/Geschäftlich/JavaKurse/Murad - Applikationsentwickler/Resources/PhotoResources/IMG_4404.jpg",
                                "no comment"),
                        new Photo("Arezzo", defaultPhotographers().get(1),
                                LocalDate.of(2015,4,2),
                                "/file/path", "another comment")
                )
        );
    }
    public final ObservableList<Photo> photos = FXCollections.observableList(defaultPhotos());
    public final ObservableList<Photographer> photographers =
            FXCollections.observableList(defaultPhotographers());
    private final ObjectProperty<Photo> selectedPhoto =
            new SimpleObjectProperty<>();

    private final ObjectProperty<Photographer> selectedPhotographer =
            new SimpleObjectProperty<>();

    public Photo getSelectedPhoto() {
        return selectedPhoto.get();
    }

    public ObjectProperty<Photo> selectedPhotoProperty() {
        return selectedPhoto;
    }

    public void setSelectedPhoto(Photo selectedPhoto) {
        this.selectedPhoto.set(selectedPhoto);
    }

    public Photographer getSelectedPhotographer() {
        return selectedPhotographer.get();
    }

    public ObjectProperty<Photographer> selectedPhotographerProperty() {
        return selectedPhotographer;
    }

    public void setSelectedPhotographer(Photographer selectedPhotographer) {
        this.selectedPhotographer.set(selectedPhotographer);
    }
}










