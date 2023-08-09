package com.example.qtacoapp.web.tacoorder;

import com.example.qtacoapp.WebConfig;
import com.example.qtacoapp.domain.tacoorder.OrderService;
import com.example.qtacoapp.utility.Utility;
import com.example.qtacoapp.web.taco.IngredientConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {OrderController.class},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = { IngredientConverter.class, WebConfig.class })})
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private MockHttpSession session;

    @BeforeEach
    void init() {
        var tacoOrder = Utility.createTacoOrder();
        session = new MockHttpSession();
        session.setAttribute("tacoOrder", tacoOrder);
    }

    @Test
    void test_show_form_with_the_taco_order() throws Exception {

        mockMvc.perform(
                        get("/orders/current").session(session)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"))
                .andExpect(content().string(containsString("thetaco")))
                .andExpect(content().string(containsString("AT +43")));

    }

    @Test
    void test_process_order_form_ok() throws Exception {


        mockMvc.perform(
                        post("/orders/current").session(session)
                                .param("firstName", "Johannes")
                                .param("lastName", "Priebsch")
                                .param("street", "Ettenreichgasse")
                                .param("streetNr", "26/18")
                                .param("city", "Wien")
                                .param("state", "Wien")
                                .param("zipCode", "1100")
                                .param("country", "Austria")
                                .param("email", "johannes@priebsch.at")
                                .param("phonePrefix", "AT")
                                .param("phone", "6505656767")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/checkout"));
    }

    @Test
    void test_process_order_form_fails_with_error() throws Exception {


        mockMvc.perform(
                        post("/orders/current").session(session)
                                .param("firstName", "")
                                .param("lastName", "Priebsch")
                                .param("street", "Ettenreichgasse")
                                .param("streetNr", "26/18")
                                .param("city", "Wien")
                                .param("state", "Wien")
                                .param("zipCode", "1100")
                                .param("country", "Austria")
                                .param("email", "johannes@priebsch.at")
                                .param("phonePrefix", "+43")
                                .param("phone", "6505656767")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"));
    }


}