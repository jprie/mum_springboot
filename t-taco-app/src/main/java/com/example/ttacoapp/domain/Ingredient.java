package com.example.ttacoapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // Mach aus Ingredient eine Table in der Datenbank
public class Ingredient {

    @Id // definiert den Primary Key
    private String id; // Flour Tortilla = FLTO
    private String name;
    @Enumerated(value = EnumType.STRING)
    private IngredientType type;
    private BigDecimal price;


}
