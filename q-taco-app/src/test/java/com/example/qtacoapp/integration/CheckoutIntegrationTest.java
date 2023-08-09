package com.example.qtacoapp.integration;

import com.example.qtacoapp.data.OrderRepository;
import com.example.qtacoapp.domain.tacoorder.TacoOrder;
import com.example.qtacoapp.utility.Utility;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckoutIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    private TacoOrder tacoOrder;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void init() {

        tacoOrder = Utility.createTacoOrder();
        var customer = Utility.createCustomer();
        tacoOrder.setCustomer(customer);

        orderRepository.save(tacoOrder);

//        entityManager.persist(tacoOrder);
    }

    @Test
    void callSuccessUrlWithToken_andReturnStoredTacoOrder() throws Exception {

        var successUrl = "http://localhost:8080/checkout/success";


        mockMvc.perform(
                        get(successUrl)
                                .param("token", tacoOrder.getToken())
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success: " + tacoOrder.getToken())));
    }
}
