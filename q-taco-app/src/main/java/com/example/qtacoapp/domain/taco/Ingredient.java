package com.example.qtacoapp.domain.taco;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

}
