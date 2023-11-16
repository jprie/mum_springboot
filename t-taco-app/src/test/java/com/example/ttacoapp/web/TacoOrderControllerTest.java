package com.example.ttacoapp.web;

import com.example.ttacoapp.domain.Taco;
import com.example.ttacoapp.domain.TacoOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {TacoOrderController.class})
public class TacoOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // TODO: Service wegmocken

    private MockHttpSession httpSession;

    private TacoOrder tacoOrder;

    @BeforeEach
    public void initSession() {
        httpSession = new MockHttpSession();
        tacoOrder = new TacoOrder();
        httpSession.setAttribute("tacoOrder", tacoOrder);
    }

    @Test
    public void showTacoOrderForm() throws Exception {

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

    @Test
    public void processTacoOrder() {


    }

}
