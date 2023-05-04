package com.example.mphotolibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Photo {

    private long id;
    private String title;
    private Photographer photographer;
    private LocalDate date;
    private String filePath;
    private String comment;


    public Photo(String title, Photographer photographer, LocalDate date, String filePath, String comment) {
        this.id = 0;
        this.title = title;
        this.photographer = photographer;
        this.date = date;
        this.filePath = filePath;
        this.comment = comment;
    }
}
