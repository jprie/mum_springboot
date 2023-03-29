package org.example;

import org.example.model.Photo;
import org.example.model.Photographer;
import org.example.repository.PhotoRepository;
import org.example.repository.PhotographerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

       var photographerRepository = new PhotographerRepository();

       List<Photographer> photographers = photographerRepository.readAll();

        Optional<Photographer> photographer = photographerRepository.readById(1);

        photographer.ifPresent(ph -> log.info("Photographer: {}", ph));

        var photos = new PhotoRepository().readAll();
        log.info("Photos: {}", photos);
    }
}











