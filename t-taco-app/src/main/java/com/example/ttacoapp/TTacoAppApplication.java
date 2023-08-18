package com.example.ttacoapp;

import com.example.ttacoapp.data.IngredientRepository;
import com.example.ttacoapp.domain.Ingredient;
import com.example.ttacoapp.domain.IngredientType;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class TTacoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TTacoAppApplication.class, args);
    }

    @Bean
    public ApplicationRunner insertIngredientsIntoDatabase(IngredientRepository ingredientRepository) {
        return (args -> {
            ingredientRepository.saveAll(TTacoAppApplication.allIngredients());
        });
    }

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
