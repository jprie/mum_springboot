package com.example.qtacoapp.data;

import com.example.qtacoapp.domain.taco.Ingredient;
import com.example.qtacoapp.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class TacoOrderRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @BeforeEach
    void initIngredients() {

        ingredientRepository.saveAll(Utility.allIngredients());
    }

    @Test
    void whenStoringTacoOrder_returnStoredTacoOrder() {

        var tacoOrder = Utility.createTacoOrder();
        var customer = Utility.createCustomer();
        tacoOrder.setCustomer(customer);

        orderRepository.save(tacoOrder);

        var foundTacoOrder = orderRepository.findTacoOrderByToken(tacoOrder.getToken()).orElseThrow();

        assertEquals(tacoOrder, foundTacoOrder);
    }
}
