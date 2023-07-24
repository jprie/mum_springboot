package com.example.qtacoapp.taco;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String name;
    private final String id;
    private final IngredientType ingredientType;
}
