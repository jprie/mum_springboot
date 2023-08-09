package com.example.rtacoadminapp.domain;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class RestIngredientService implements IngredientService {

    private final RestTemplate restTemplate;
    private final String tacoApiUrl = "http://localhost:8080/api/ingredients";

    public RestIngredientService() {
        this.restTemplate = new RestTemplate();
    }


    @Override
    public List<Ingredient> allIngredients() {
        return Arrays.asList(restTemplate.getForObject(tacoApiUrl, Ingredient[].class));

    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return restTemplate.postForObject(tacoApiUrl, ingredient, Ingredient.class);
    }

    @Override
    public void deleteIngredient(String id) {

        restTemplate.delete(tacoApiUrl + "/{id}", id);
    }
}
