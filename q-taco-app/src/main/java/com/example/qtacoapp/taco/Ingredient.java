package com.example.qtacoapp.taco;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Ingredient {

    public static final List<Ingredient> ingredients = List.of(
            new Ingredient("White Bun", "WBUN", IngredientType.BUN),
                new Ingredient("Rye Bun", "RBUN", IngredientType.BUN),
                new Ingredient("Rucola", "RUCO", IngredientType.SALAD),
                new Ingredient("Lettuce", "LETT", IngredientType.SALAD),
                new Ingredient("Chicken", "CHIC", IngredientType.MEAT),
                new Ingredient("Burger", "BURG", IngredientType.MEAT),
                new Ingredient("Mozzarella", "MOZZ", IngredientType.CHEESE),
                new Ingredient("Gouda", "GOUD", IngredientType.CHEESE),
                new Ingredient("Red Sauce", "RSAU", IngredientType.SAUCE),
                new Ingredient("Sour creme", "SOUR", IngredientType.SAUCE),
                new Ingredient("Cucumber", "CUCU", IngredientType.VEGGIE),
                new Ingredient("Tomatoes", "TOMA", IngredientType.VEGGIE)
        );
    private final String name;
    private final String id;
    private final IngredientType ingredientType;
}
