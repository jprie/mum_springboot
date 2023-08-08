package com.example.qtacoapp.taco;


import com.example.qtacoapp.taco.Taco;
import com.example.qtacoapp.taco.TacoDesignController;
import com.example.qtacoapp.tacoorder.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {TacoDesignController.class})
public class TacoDesignControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_process_new_taco_successful() throws Exception {

        var newTaco = new Taco();
        mockMvc.perform(
                        post("/design")
                                .param("name", "newTaco")
                                .param("ingredients", "WBUN")
                                .param("ingredients", "RUCO")
                                .param("ingredients", "TOMA")
                                .param("ingredients", "SOUR")
                                .param("ingredients", "BURG")
//                                .param("ingredients11", "MOZZ")


                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/orders/current"));
    }

    @Test
    void test_process_new_taco_fails_no_name() throws Exception {

        mockMvc.perform(
                        post("/design")
                                .param("ingredients", "WBUN")
                                .param("ingredients", "RUCO")
                                .param("ingredients", "TOMA")
                                .param("ingredients", "SOUR")
                                .param("ingredients", "BURG")
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
                                .param("ingredients", "WBUN")
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
                                .param("ingredients", "WBUN")
                                .param("ingredients", "RUCO")
                                .param("ingredients", "TOMA")

                )
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(content().string(containsString("not enough ingredients")));
    }

}
