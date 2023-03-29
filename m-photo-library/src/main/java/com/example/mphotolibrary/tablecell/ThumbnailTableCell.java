package com.example.mphotolibrary.tablecell;

import com.example.mphotolibrary.model.Photo;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ThumbnailTableCell extends TableCell<Photo, ImageView> {
    ImageView thumbnailImageView = new ImageView();
    @Override
    protected void updateItem(ImageView imageView, boolean empty) {
        super.updateItem(imageView, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(null);
            Photo photo = getTableView().getItems().get(getIndex());
            Image image = loadImage(photo.getFilePath());
            thumbnailImageView.setImage(image);
            thumbnailImageView.setPreserveRatio(true);
            thumbnailImageView.setFitHeight(80.0);

            setGraphic(thumbnailImageView);
        }
    }
    private Image loadImage(String filePath) {
        try (InputStream inputStream = Files.newInputStream(Path.of(filePath))) {
            return new Image(inputStream);
        } catch (IOException e) {
            System.err.println("Could not load image: " + filePath);
        }
        return null;
    }
}
