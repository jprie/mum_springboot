package com.example.qtacoapp.web.taco;


import com.example.qtacoapp.data.IngredientRepository;
import com.example.qtacoapp.domain.taco.Ingredient;
import com.example.qtacoapp.domain.taco.Taco;
import com.example.qtacoapp.utility.Utility;
import com.example.qtacoapp.web.taco.TacoDesignController;
import com.example.qtacoapp.domain.tacoorder.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {TacoDesignController.class})
public class TacoDesignControllerTest {

    @MockBean
    private OrderService orderService;

    @MockBean
    private IngredientRepository ingredientRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void initIngredientRepositoryMock() {

        for (var id : Utility.allIngredients().stream().map(Ingredient::getId).toList()) {

            when(ingredientRepository.findById(id))
                    .thenReturn(Optional.ofNullable(Utility.allIngredientsMap().get(id).get(0)));
        }

        when(ingredientRepository.findAll()).thenReturn(Utility.allIngredients());
    }

    @Test
    void callDesignPage_andReturnDesignPageContainingEntireMenu() throws Exception {

        var ingredientMenuMatchers = Utility.allIngredients().stream()
                        .map(Ingredient::getName)
                .map(name -> content().string(containsString(name)))
                                .toList();
        mockMvc.perform(
                get("/design")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpectAll(
                        ingredientMenuMatchers.toArray(new ResultMatcher[12])
                );
    }
    @Test
    void test_process_new_taco_successful() throws Exception {


        var newTaco = new Taco();
        mockMvc.perform(
                        post("/design")
                                .param("name", "newTaco")
                                .param("ingredients", "FLTO")
                                .param("ingredients", "RUCO")
                                .param("ingredients", "TOMA")
                                .param("ingredients", "SOUR")
                                .param("ingredients", "BEAF")
//                                .param("ingredients11", "MOZZ")


                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/orders/current"));
    }

    @Test
    void test_process_new_taco_fails_no_name() throws Exception {

        mockMvc.perform(
                        post("/design")
                                .param("ingredients", "FLTO")
                                .param("ingredients", "RUCO")
                                .param("ingredients", "TOMA")
                                .param("ingredients", "SOUR")
                                .param("ingredients", "BEAF")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(content().string(containsString("no name given")));
    }

    @Test
    void test_process_new_taco_fails_name_2_chars() throws Exception {

        mockMvc.perform(
                        post("/design")
                                .param("name", "th")
                                .param("ingredients", "FLTO")
                                .param("ingredients", "RUCO")
                                .param("ingredients", "TOMA")

                )
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(content().string(containsString("name must be 3 characters or longer")));
    }

    @Test
    void test_process_new_taco_fails_only_3_ingredients() throws Exception {

        mockMvc.perform(
                        post("/design")
                                .param("name", "thetaco")
                                .param("ingredients", "FLTO")
                                .param("ingredients", "RUCO")
                                .param("ingredients", "TOMA")

                )
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(content().string(containsString("not enough ingredients")));
    }

}
