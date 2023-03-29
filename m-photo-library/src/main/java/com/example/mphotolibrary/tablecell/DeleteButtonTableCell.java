package com.example.mphotolibrary.tablecell;

import com.example.mphotolibrary.BaseController;
import com.example.mphotolibrary.model.Photo;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class DeleteButtonTableCell extends TableCell<Photo, Button> {

    Button deleteButton = new Button("Löschen");
    @Override
    protected void updateItem(Button button, boolean empty) {
        super.updateItem(button, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(null);
            deleteButton.setOnAction(event ->
                    BaseController.model.photos.remove(getIndex()));
            setGraphic(deleteButton);
        }

    }
}
