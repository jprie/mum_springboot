package com.example.ttacoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String id; // Flour Tortilla = FLTO
    private String name;
    private IngredientType type;
    private BigDecimal price;

}
