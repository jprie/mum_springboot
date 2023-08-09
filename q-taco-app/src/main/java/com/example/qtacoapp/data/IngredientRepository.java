package com.example.qtacoapp.data;

import com.example.qtacoapp.domain.taco.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface IngredientRepository extends ListCrudRepository<Ingredient, String> {
}
