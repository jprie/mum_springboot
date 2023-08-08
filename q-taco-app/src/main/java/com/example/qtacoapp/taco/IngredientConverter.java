package com.example.qtacoapp.taco;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientsMap;
    public IngredientConverter() {
        ingredientsMap = Ingredient.ingredients
                .stream()
                .collect(Collectors.toMap(Ingredient::getId, ingredient -> ingredient));
        log.info("Ingredients: {}", ingredientsMap);

    }

    @Override
    public Ingredient convert(String source) {

        log.info("Converting: {}", source);
        return ingredientsMap.get(source);
    }
}
