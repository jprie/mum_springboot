package com.example.ttacoapp.web;


import com.example.ttacoapp.data.IngredientRepository;
import com.example.ttacoapp.domain.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public IngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public Ingredient convert(String id) {

        return ingredientRepository.findById(id).orElse(null);
    }
}
