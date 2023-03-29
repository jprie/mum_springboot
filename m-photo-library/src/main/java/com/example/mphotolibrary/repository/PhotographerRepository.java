package com.example.mphotolibrary.repository;

import org.example.model.Photographer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhotographerRepository implements CrudRepository<Photographer> {

    private static final Logger log = LoggerFactory.getLogger(PhotographerRepository.class);
    private static final String SQL_INSERT_PHOTOGRAPHER = """
            INSERT INTO PHOTOGRAPHER (first_name, last_name) VALUES (?,?);
            """;
    private static final String SQL_UPDATE_PHOTOGRAPHER = """
            UPDATE PHOTOGRAPHER SET first_name=?, last_name=? WHERE id=?;
            """;
    public static final String DELETE_FROM_PHOTOGRAPHER = "DELETE FROM Photographer WHERE id=?";
    public static final String SELECT_FROM_PHOTOGRAPHER = "SELECT * FROM Photographer";
    public static final String SELECT_FROM_PHOTOGRAPHER_WHERE_ID = "SELECT * FROM Photographer WHERE id=?";

    @Override
    public Photographer create(Photographer entity) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PHOTOGRAPHER,
                                                                                    Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, entity.getFirstName());
                preparedStatement.setString(2, entity.getLastName());
                int rowsUpdated = preparedStatement.executeUpdate();
                log.info("Rows updated");

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

    @Override
    public Optional<Photographer> readById(long id) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_PHOTOGRAPHER_WHERE_ID)) {

                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    if (resultSet.next()) {
                        Photographer photographer = photographerFromResultSet(resultSet);
                        return Optional.of(photographer);
                    }
                }
            }
        } catch(SQLException e) {
            System.err.println(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Photographer> readAll() {

        List<Photographer> photographers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
//            System.out.println("Connection established");
            log.info("Connection established");

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery(SELECT_FROM_PHOTOGRAPHER)) {

                    while (resultSet.next()) {

                        Photographer photographer = photographerFromResultSet(resultSet);
                        log.info("Photographer from DB: {}", photographer);
                        photographers.add(photographer);
                    }
                }
            }

        } catch (SQLException e) {
            log.error("Could not connect to DB");
        }
        return photographers;
    }

    private static Photographer photographerFromResultSet(ResultSet resultSet) throws SQLException {
        // Übersetzung von relation database -> OOP
        long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        Photographer photographer = new Photographer(id, firstName, lastName);
        return photographer;
    }

    @Override
    public Photographer update(Photographer entity) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PHOTOGRAPHER)) {

                preparedStatement.setString(1, entity.getFirstName());
                preparedStatement.setString(2, entity.getLastName());
                preparedStatement.setLong(3, entity.getId());
                int rowsUpdated = preparedStatement.executeUpdate();
                log.info("Rows updated: {}", rowsUpdated);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    @Override
    public void delete(Photographer entity) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_PHOTOGRAPHER)) {

                preparedStatement.setLong(1, entity.getId());
                int rowsUpdated = preparedStatement.executeUpdate();
                log.info("Rows updated {}", rowsUpdated);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}












