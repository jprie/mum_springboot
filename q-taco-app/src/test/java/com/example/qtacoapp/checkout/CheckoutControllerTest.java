package com.example.qtacoapp.checkout;

import com.example.qtacoapp.data.OrderRepository;
import com.example.qtacoapp.shared.Price;
import com.example.qtacoapp.tacoorder.OrderService;
import com.example.qtacoapp.tacoorder.PricedTaco;
import com.example.qtacoapp.tacoorder.TacoOrder;
import com.example.qtacoapp.utility.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@AutoConfigureMockMvc
class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private PaymentService paymentService;

    private TacoOrder tacoOrder;
    private MockHttpSession session;

    @BeforeEach
    void setUp() {

        tacoOrder = Utility.createTacoOrder();
        tacoOrder.setCustomer(Utility.createCustomer());
        tacoOrder.setPrice(new Price(
                new BigDecimal("3.6"),
                Currency.getInstance("EUR")
        ));
        session = new MockHttpSession();
        session.setAttribute("tacoOrder", tacoOrder);

    }

    @Test
    void show_checkout_with_prices() throws Exception {

        when(orderService.calculatePrices(tacoOrder)).thenReturn(List.of(new BigDecimal("3.6")));
        when(orderService.pricedTacos(tacoOrder)).thenReturn(List.of(
                new PricedTaco("thetaco", new BigDecimal("3.6")))
        );
        when(orderService.calculateSum(tacoOrder)).thenReturn(Optional.of(new BigDecimal("3.6")));

        mockMvc.perform(
                        get("/checkout")
                                .session(session)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("checkout"))
                .andExpect(content().string(containsString("thetaco")))
                .andExpect(content().string(containsString("3.6")))
                .andExpect(content().string(containsString("Choose payment provider")))
                .andExpect(content().string(containsString("Visa")));
    }

    @Test
    void process_checkout() throws Exception {

        var id = 1;
        var successUrl = "http://localhost:8080/checkout/success";
        var paymentProvider = new PaymentProvider("Visa", "http://localhost:9090/visa");
//        var redirectedUrl = paymentProvider.url() + "?sellerInfo=taco_cloud_xyz%2BTacoCloud" +
//                "&price=EUR%2B3.6" + "&token=" + tacoOrder.getToken() + "&successUrl=" + URLEncoder.encode(successUrl, StandardCharsets.UTF_8.toString());

        record Tuple(String key, String value) {}
        var params = List.of(
            new Tuple("sellerInfo", "taco_cloud_xyz+TacoCloud"),
            new Tuple("price", "EUR+3.6"),
            new Tuple("token", tacoOrder.getToken()),
            new Tuple("successUrl", successUrl)
        );

        var paramStringEncoded = params.stream()
                        .map(tp -> tp.key() + "=" + encodeValue(tp.value()))
                                .collect(Collectors.joining("&"));

        var redirectedUrl = paymentProvider.url() + "?" + paramStringEncoded;

        when(paymentService.processPaymentInfo(
                        new PaymentProviderForm("Visa", true),
                        tacoOrder
                )
        ).thenReturn(paymentProvider);

        doAnswer(invocation -> {

            // test if storeOrder was called - i.e. id is set to 1
            var tacoOrder = invocation.getArgument(0, TacoOrder.class);
            tacoOrder.setId(1l);
            return tacoOrder;

        }).when(orderService).storeOrder(tacoOrder);

        mockMvc.perform(
                        post("/checkout")
                                .param("name", "Visa")
                                .param("store", "true")
                                .session(session)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(redirectedUrl));

        verify(orderService, times(1)).storeOrder(tacoOrder);
        assertEquals(tacoOrder.getId(), 1l);

    }

    private String encodeValue(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }
}