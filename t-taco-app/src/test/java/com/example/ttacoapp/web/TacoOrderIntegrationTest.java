package com.example.ttacoapp.web;

import com.example.ttacoapp.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.ttacoapp.TestUtilities.tacoForm;
import static com.example.ttacoapp.TestUtilities.tacoOrderFromForm;
import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TacoOrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private TacoOrder tacoOrder;

    private MockHttpSession httpSession;

    @BeforeEach
    void initSession() {
        httpSession = new MockHttpSession();
        tacoOrder = new TacoOrder();
        httpSession.setAttribute("tacoOrder", tacoOrder);
    }

    @Test
    void showOrderForm() throws Exception {

        var taco = new Taco();
        taco.setName("my taco");
        tacoOrder.setTacos(List.of(taco));

        mockMvc.perform(
                        get("/orders/current").session(httpSession)
                )
                .andExpect(status().isOk()) // status = 200
                .andExpect(view().name("tacoOrderForm"))
                .andExpect(content().string(containsString("my taco")));
    }

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void processOrder() throws Exception {

        // taco Order in der Session
        tacoOrder = new TacoOrder();
        tacoOrder.setTacos(List.of(tacoForm()));

        httpSession = new MockHttpSession();
        httpSession.setAttribute("tacoOrder", tacoOrder);

        mockMvc.perform(
                        post("/orders")
                                .session(httpSession)
                                .content(objectMapper.writeValueAsString(tacoOrderFromForm())) // tacoOrder mit ausgefüllter Form
                )
                .andExpect(status().isOk())
                .andExpect(view().name("orderSummary"));

    }


}