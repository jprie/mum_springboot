package com.example.qtacoapp.web.api;

import com.example.qtacoapp.data.IngredientRepository;
import com.example.qtacoapp.domain.taco.Ingredient;
import com.example.qtacoapp.domain.taco.IngredientType;
import com.example.qtacoapp.utility.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {IngredientController.class})
public class IngredientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IngredientRepository ingredientRepository;

    @Test
    void getAllIngredients_returnsAllIngredients() throws Exception {

        when(ingredientRepository.findAll()).thenReturn(Utility.allIngredients());

        mockMvc.perform(
                        get("/api/ingredients")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Utility.allIngredients())));
    }

    @Test
    void getIngredientTMTO_returnsTomato() throws Exception {

        Ingredient ingredient = new Ingredient("Tomatoes", "TMTO", IngredientType.VEGGIE, "0.5");

        when(ingredientRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));

        mockMvc.perform(
                        get("/api/ingredients/{id}", ingredient.getId())
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(ingredient)));
    }

    @Test
    void getIngredientXYZ_returnsBadRequestWithError() throws Exception {

        Ingredient ingredient = new Ingredient("Tomatoes", "XYZ", IngredientType.VEGGIE, "0.5");

        when(ingredientRepository.findById(ingredient.getId())).thenReturn(Optional.empty());

        IngredientControllerAdvice.Error error = new IngredientControllerAdvice.Error("Ingredient " + ingredient.getId() + " not found");
        mockMvc.perform(
                        get("/api/ingredients/{id}", ingredient.getId())
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(error)));
    }

    @Test
    void saveNewIngredientShrimps_returnsHttpCreated() throws Exception {

        Ingredient ingredient = new Ingredient("Shrimps", "SHRP", IngredientType.PROTEIN, "0.7");

        when(ingredientRepository.save(ingredient)).thenReturn(ingredient);

        mockMvc.perform(
                        post("/api/ingredients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(ingredient))
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(ingredient)));

    }

    @Test
    void deleteExistingIngredientTomatoes_returnsNoContent() throws Exception {

        var id = "TMTO";

        doNothing().when(ingredientRepository).deleteById(id);

        mockMvc.perform(
                        request(HttpMethod.DELETE, "/api/ingredients/{id}", id)
                )
                .andExpect(status().isNoContent());

    }

}
