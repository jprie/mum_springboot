package com.example.qtacoapp.unit;

import com.example.qtacoapp.domain.customer.Address;
import com.example.qtacoapp.domain.customer.Customer;
import com.example.qtacoapp.domain.customer.EmailAddress;
import com.example.qtacoapp.domain.customer.PhoneNumber;
import com.example.qtacoapp.data.OrderRepository;
import com.example.qtacoapp.domain.tacoorder.OrderService;
import com.example.qtacoapp.domain.tacoorder.TacoOrder;
import com.example.qtacoapp.utility.Utility;
import com.example.qtacoapp.web.tacoorder.OrderForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private OrderService orderService;
    private TacoOrder tacoOrder;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void init() {

        orderService = new OrderService(orderRepository);
        tacoOrder = Utility.createTacoOrder();

    }

    @Test
    void test_process_taco_order_with_form() {

        var orderForm = OrderForm.builder()
                .firstName("Johannes")
                .lastName("Priebsch")
                .street("Ettenreichgasse")
                .streetNr("26/18")
                .city("Wien")
                .state("VI")
                .zipCode(1100)
                .country("Austria")
                .email("johannes@priebsch.at")
                .phonePrefix("+43")
                .phone("6505656767")
                .build();
        var customerFromForm = Customer.builder()
                .firstName("Johannes")
                .lastName("Priebsch")
                .address(new Address("Ettenreichgasse",
                        "26/18",
                        "Wien",
                        "VI",
                        1100,
                        "Austria")
                )
                .email(new EmailAddress("johannes@priebsch.at"))
                .phone(new PhoneNumber("+43", "6505656767"))
                .paymentProvider(null)
                .build();
        orderService.process(orderForm, tacoOrder);
        assertEquals(customerFromForm, tacoOrder.getCustomer());
    }

    @Test
    void test_priced_tacos() {

        var pricedTacos = orderService.pricedTacos(tacoOrder);
        var pricedTaco = pricedTacos.get(0);
        assertEquals("thetaco", pricedTaco.name());
        assertEquals(new BigDecimal("3.6"), pricedTaco.price());
    }

    @Test
    void test_calculate_price() {

        var prices = orderService.calculatePrices(tacoOrder);
        assertEquals(List.of(new BigDecimal("3.6")), prices);
    }

    @Test
    void calculateSum() {

        var sum = orderService.calculateSum(tacoOrder).orElseThrow();
        assertEquals(new BigDecimal("3.6"), sum);
    }
}