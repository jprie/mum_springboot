package com.example.qtacoapp.taco;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientsMap;
    public IngredientConverter() {
        ingredientsMap = Ingredient.ingredients
                .stream()
                .collect(Collectors.toMap(Ingredient::getName, ingredient -> ingredient));

    }

    @Override
    public Ingredient convert(String source) {
        return ingredientsMap.get(source);
    }
}
