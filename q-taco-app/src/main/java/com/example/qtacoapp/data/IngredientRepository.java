package com.example.qtacoapp.data;

import com.example.qtacoapp.taco.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
