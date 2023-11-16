package com.example.ttacoapp.web;

import com.example.ttacoapp.TestUtilities;
import com.example.ttacoapp.data.IngredientRepository;
import com.example.ttacoapp.domain.Taco;
import com.example.ttacoapp.domain.TacoOrder;
import com.example.ttacoapp.service.TacoOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static com.example.ttacoapp.TestUtilities.tacoForm;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {TacoOrderController.class})
public class TacoOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // TODO: Service wegmocken
    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private TacoOrderService tacoOrderService;

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
    public void processTacoOrder() throws Exception {

        tacoOrder = TestUtilities.tacoOrderEntity();
        // Mock-Implementierung: TacoOrderService
        when(tacoOrderService.calculateTacoPrices(tacoOrder))
                .thenReturn(List.of(new TacoOrderService.TacoNameAndPrice("my taco", new BigDecimal("1.6"))));
        when(tacoOrderService.calculateSum(tacoOrder))
                .thenReturn(new BigDecimal("1.6"));


        // taco Order in der Session
        tacoOrder = new TacoOrder();
        tacoOrder.setTacos(List.of(tacoForm()));

        httpSession = new MockHttpSession();
        httpSession.setAttribute("tacoOrder", tacoOrder);

        mockMvc.perform(
                        post("/orders")
                                .session(httpSession)
                                .param("firstName", "Johannes")
                                .param("lastName", "Priebsch")// tacoOrder mit ausgefüllter Form
                )
                .andExpect(status().isOk())
                .andExpect(view().name("orderSummary"))
                .andExpect(content().string(containsString("1.6")))
                .andExpect(content().string(containsString("my taco")));


    }

}
