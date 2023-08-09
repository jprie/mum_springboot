package com.example.rtacoadminapp.domain;

import com.example.rtacoadminapp.domain.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> allIngredients();

    Ingredient saveIngredient(Ingredient ingredient);

    void deleteIngredient(String id);

}
