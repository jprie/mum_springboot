package com.example.mphotolibrary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PhotoLibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        loadFXMLViewInStage("photo-view.fxml", stage);
    }

    public static void loadFXMLViewInStage(String fxmlView, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotoLibraryApplication.class.getResource(fxmlView));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PhotoLibrary");
        stage.setScene(scene);
        stage.show();
    }

    public static void loadFXMLViewInStageAndWait(String fxmlView, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PhotoLibraryApplication.class.getResource(fxmlView));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PhotoLibrary");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}