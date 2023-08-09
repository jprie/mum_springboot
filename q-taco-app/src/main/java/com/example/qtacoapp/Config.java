package com.example.qtacoapp;

import com.example.qtacoapp.data.IngredientRepository;
import com.example.qtacoapp.domain.taco.Ingredient;
import com.example.qtacoapp.domain.taco.IngredientType;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {

    @Bean
    public ApplicationRunner applicationRunner(IngredientRepository ingredientRepository) {

        return (args) -> {
            ingredientRepository.saveAll(
                    List.of(
                            new Ingredient("Flour Tortilla", "FLTO", IngredientType.WRAP, "1.5"),
                            new Ingredient("Corn Tortilla", "COTO", IngredientType.WRAP, "1.5"),
                            new Ingredient("Rucola", "RUCO", IngredientType.SALAD, ".3"),
                            new Ingredient("Lettuce", "LETT", IngredientType.SALAD, ".3"),
                            new Ingredient("Chicken", "CHIC", IngredientType.PROTEIN, ".7"),
                            new Ingredient("Ground Beaf", "BEAF", IngredientType.PROTEIN, ".7"),
                            new Ingredient("Mozzarella", "MOZZ", IngredientType.CHEESE, ".4"),
                            new Ingredient("Gouda", "GOUD", IngredientType.CHEESE, ".4"),
                            new Ingredient("Red Sauce", "RSAU", IngredientType.SAUCE, ".2"),
                            new Ingredient("Sour creme", "SOUR", IngredientType.SAUCE, ".2"),
                            new Ingredient("Cucumber", "CUCU", IngredientType.VEGGIE, ".4"),
                            new Ingredient("Tomatoes", "TOMA", IngredientType.VEGGIE, ".4")
                    )
            );
        };

    }
}
