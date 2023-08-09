package com.example.qtacoapp.web.taco;

import com.example.qtacoapp.data.IngredientRepository;
import com.example.qtacoapp.domain.taco.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class IngredientConverter implements Converter<String, Ingredient> {
    private final IngredientRepository ingredientRepository;

    public IngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String source) {

        log.info("Converting: {}", source);
        return ingredientRepository.findById(source).orElseThrow();
    }
}
