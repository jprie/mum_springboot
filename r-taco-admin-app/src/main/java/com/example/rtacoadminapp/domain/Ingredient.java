package com.example.rtacoadminapp.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @NotBlank
    String name;

    @NotBlank
    String id;

    IngredientType ingredientType;

    @DecimalMin("0.1")
    BigDecimal price;

    public Ingredient(String name, String id, IngredientType ingredientType, String price) {
        this(name, id, ingredientType, new BigDecimal(price));
    }

}
