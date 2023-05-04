package com.example.mphotolibrary.model;

import com.example.mphotolibrary.repository.PhotoRepository;
import com.example.mphotolibrary.repository.PhotographerRepository;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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

    private final PhotographerRepository photographerRepository = new PhotographerRepository();
    private final PhotoRepository photoRepository = new PhotoRepository();

    public final ObservableList<Photo> photos = FXCollections.observableList(photoRepository.readAll());
    public final ObservableList<Photographer> photographers =
            FXCollections.observableList(photographerRepository.readAll());
    private final ObjectProperty<Photo> selectedPhoto =
            new SimpleObjectProperty<>();

    private final ObjectProperty<Photographer> selectedPhotographer =
            new SimpleObjectProperty<>();

    public PhotoLibraryModel() {

        // registriere Listener auf den ObservableLists
        // 'direkte' Verbindung photos zu photo-Tabelle: 1-zu-1 jede Operation wird direkt in der Datenbank übernommen
        photos.addListener((ListChangeListener<Photo>) change -> {
            while(change.next()) {
                if (change.wasReplaced()) change.getAddedSubList().forEach(photoRepository::update);
                else if (change.wasAdded()) change.getAddedSubList().forEach(photoRepository::create);
                else if (change.wasRemoved()) change.getRemoved().forEach(photoRepository::delete);
            }
        });

        photographers.addListener((ListChangeListener<Photographer>) change -> {
            while(change.next()) {
                if (change.wasReplaced()) change.getAddedSubList().forEach(photographerRepository::update);
                else if (change.wasAdded()) change.getAddedSubList().forEach(photographerRepository::create);
                else if (change.wasRemoved()) change.getRemoved().forEach(photographerRepository::delete);
            }
        });
    }

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










