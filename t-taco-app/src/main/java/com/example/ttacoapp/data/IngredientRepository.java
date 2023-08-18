package com.example.ttacoapp.data;

import com.example.ttacoapp.domain.Ingredient;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

// Das Repository erweitert das ListCrudRepository-Interface von spring-data
// Dadurch werden alle CRUD Methoden im Hintergrund implementiert
// Und zwar für den angegebenen Typ samt Typ der id
public interface IngredientRepository extends ListCrudRepository<Ingredient, String> {

    /*List<Ingredient> findAll();*/
}
