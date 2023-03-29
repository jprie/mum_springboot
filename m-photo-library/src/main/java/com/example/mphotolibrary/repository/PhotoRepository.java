package com.example.mphotolibrary.repository;

import org.example.model.Photo;
import org.example.model.Photographer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhotoRepository implements CrudRepository<Photo> {

    private static final Logger log = LoggerFactory.getLogger(PhotoRepository.class);
    private static final String SQL_INSERT_PHOTO = """
            INSERT INTO Photo 
            (title, photographer_id, date, file_path, comment) 
            VALUES (?,?,?,?,?);
            """;
    private static final String SQL_UPDATE_PHOTO = """
            UPDATE Photo SET 
            title=?, photographer_id=?, date=?, file_path=?, comment=? WHERE id=?;
            """;
    public static final String DELETE_FROM_PHOTO = "DELETE FROM Photo WHERE id=?";
    public static final String SELECT_FROM_PHOTO = """
            SELECT Photo.*, Photographer.first_name, Photographer.last_name FROM Photo
            JOIN Photographer
            ON photographer_id=Photographer.id
    """;
    public static final String SELECT_FROM_PHOTO_WHERE_ID = SELECT_FROM_PHOTO + " WHERE id=?";


    @Override
    public Photo create(Photo entity) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PHOTO,
                                                                                    Statement.RETURN_GENERATED_KEYS)) {

                prepareStatement(entity, preparedStatement);
                int rowsUpdated = preparedStatement.executeUpdate();
                log.info("Rows updated: {}", rowsUpdated);

                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();
                    long id = resultSet.getLong("id");
                    entity.setId(id);
                }
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return entity;
    }

    private static void prepareStatement(Photo entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getTitle());
        preparedStatement.setLong(2, entity.getPhotographer().getId());
        preparedStatement.setDate(3, Date.valueOf(entity.getDate()));
        preparedStatement.setString(4, entity.getFilePath());
        preparedStatement.setString(5, entity.getComment());
    }

    @Override
    public Optional<Photo> readById(long id) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_PHOTO_WHERE_ID)) {

                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    if (resultSet.next()) {
                        Photo photo = photoFromResultSet(resultSet);
                        return Optional.of(photo);
                    }
                }
            }
        } catch(SQLException e) {
            System.err.println(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Photo> readAll() {

        List<Photo> photos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
//            System.out.println("Connection established");
            log.info("Connection established");

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery(SELECT_FROM_PHOTO)) {

                    while (resultSet.next()) {

                        Photo photo = photoFromResultSet(resultSet);
                        log.info("Photo from DB: {}", photo);
                        photos.add(photo);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return photos;
    }

    private static Photo photoFromResultSet(ResultSet resultSet) throws SQLException {
        // Übersetzung von relation database -> OOP
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        long photographerId = resultSet.getLong("photographer_id");
        LocalDate date = resultSet.getDate("date").toLocalDate();
        String filePath = resultSet.getString("file_path");
        String comments = resultSet.getString("comment");

        // Photographer
        String firstName = resultSet.getString("Photographer.first_name");
        String lastName = resultSet.getString("Photographer.last_name");
        Photographer photographer = new Photographer(photographerId, firstName, lastName);

        Photo photo = new Photo(id, title, photographer, date, filePath, comments);
        return photo;
    }

    @Override
    public Photo update(Photo entity) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PHOTO)) {

                prepareStatement(entity, preparedStatement);
                preparedStatement.setLong(6, entity.getId());
                int rowsUpdated = preparedStatement.executeUpdate();
                log.info("Rows updated: {}", rowsUpdated);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public void delete(Photo entity) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_PHOTO)) {

                preparedStatement.setLong(1, entity.getId());
                int rowsUpdated = preparedStatement.executeUpdate();
                log.info("Rows updated {}", rowsUpdated);
                
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}












