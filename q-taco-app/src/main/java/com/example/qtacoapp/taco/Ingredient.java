package com.example.qtacoapp.taco;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    String name;
    @Id String id;
    IngredientType ingredientType;
    BigDecimal price;

    public Ingredient(String name, String id, IngredientType ingredientType, String price) {
        this(name, id, ingredientType, new BigDecimal(price));
    }

    public static final List<Ingredient> ingredients = List.of(
            new Ingredient("White Bun", "WBUN", IngredientType.BUN, "1.5"),
            new Ingredient("Rye Bun", "RBUN", IngredientType.BUN, "1.5"),
            new Ingredient("Rucola", "RUCO", IngredientType.SALAD, ".3"),
            new Ingredient("Lettuce", "LETT", IngredientType.SALAD, ".3"),
            new Ingredient("Chicken", "CHIC", IngredientType.MEAT, ".7"),
            new Ingredient("Burger", "BURG", IngredientType.MEAT, ".7"),
            new Ingredient("Mozzarella", "MOZZ", IngredientType.CHEESE, ".4"),
            new Ingredient("Gouda", "GOUD", IngredientType.CHEESE, ".4"),
            new Ingredient("Red Sauce", "RSAU", IngredientType.SAUCE, ".2"),
            new Ingredient("Sour creme", "SOUR", IngredientType.SAUCE, ".2"),
            new Ingredient("Cucumber", "CUCU", IngredientType.VEGGIE, ".4"),
            new Ingredient("Tomatoes", "TOMA", IngredientType.VEGGIE, ".4")
    );

}
