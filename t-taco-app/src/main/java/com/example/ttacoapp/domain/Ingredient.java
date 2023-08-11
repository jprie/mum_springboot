package com.example.ttacoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String id; // Flour Tortilla = FLTO
    private String name;
    private IngredientType type;
    private BigDecimal price;

    public static List<Ingredient> allIngredients() {

        return List.of(
                new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP, new BigDecimal("0.5")),
                new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP, new BigDecimal("0.6")),
                new Ingredient("TMTO", "Tomatoes", IngredientType.VEGGIE, new BigDecimal("0.2")),
                new Ingredient("LETT", "Lettuce", IngredientType.VEGGIE, new BigDecimal("0.3")),
                new Ingredient("BEEF", "Ground Beef", IngredientType.MEAT, new BigDecimal("0.5")),
                new Ingredient("CHIC", "Grilled Chicken", IngredientType.MEAT, new BigDecimal("0.4")),
                new Ingredient("RSAU", "Red Sauce", IngredientType.SAUCE, new BigDecimal("0.2")),
                new Ingredient("SCRE", "Sour Creme", IngredientType.SAUCE, new BigDecimal("0.1")),
                new Ingredient("CHED", "Cheddar Cheese", IngredientType.CHEESE, new BigDecimal("0.3")),
                new Ingredient("GORG", "Gorgonzola", IngredientType.CHEESE, new BigDecimal("0.3"))
        );
    }
}
