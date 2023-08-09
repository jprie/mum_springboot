package com.example.qtacoapp.unit;

import com.example.qtacoapp.domain.payment.PaymentProvider;
import com.example.qtacoapp.domain.payment.PaymentService;
import com.example.qtacoapp.domain.tacoorder.OrderService;
import com.example.qtacoapp.domain.tacoorder.TacoOrder;
import com.example.qtacoapp.utility.Utility;
import com.example.qtacoapp.web.checkout.PaymentProviderForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {


    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private OrderService orderService;

    private TacoOrder tacoOrder;

    @BeforeEach
    void init() {
        paymentService = new PaymentService(orderService);
        var customer = Utility.createCustomer();
        tacoOrder = Utility.createTacoOrder();
        tacoOrder.setCustomer(customer);
    }

    @Test
    void test_process_payment_form() {

        var paymentProvider = PaymentProvider.allProvidersMap().get("Visa");
        var paymentProviderForm = new PaymentProviderForm("Visa", true);


        doAnswer((invocation) -> {

            if (invocation.getArguments()[1] instanceof TacoOrder order) {
                order.getCustomer().setPaymentProvider(paymentProvider);
                return order;
            }
            return null;
        }).when(orderService).storePaymentInfo(PaymentProvider.allProvidersMap().get("Visa"), tacoOrder);

        paymentService.processPaymentInfo(paymentProviderForm, tacoOrder);
        System.out.println("PP: " + paymentProvider);
        assertEquals(paymentProvider, tacoOrder.getCustomer().getPaymentProvider());
    }

}