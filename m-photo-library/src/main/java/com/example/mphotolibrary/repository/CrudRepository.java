package com.example.mphotolibrary.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    static final String PASSWORD = "";
    static final String DB_URL = "jdbc:mariadb://localhost:3306/photos";
    static final String USERNAME = "root";

    // CRUD
    T create(T entity);

    Optional<T> readById(long id);

    List<T> readAll();

    T update(T entity);

    void delete(T entity);
}










