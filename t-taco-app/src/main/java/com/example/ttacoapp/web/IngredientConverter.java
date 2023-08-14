package com.example.ttacoapp.web;


import com.example.ttacoapp.domain.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap;

    public IngredientConverter() {
        this.ingredientMap = Ingredient.allIngredients().stream()
                .collect(Collectors.toMap(Ingredient::getId, ingredient -> ingredient));
    }

    @Override
    public Ingredient convert(String id) {

        return ingredientMap.get(id);
    }
}
