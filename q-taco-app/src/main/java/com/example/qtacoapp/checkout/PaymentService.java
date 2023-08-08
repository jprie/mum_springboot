package com.example.qtacoapp.checkout;

import com.example.qtacoapp.tacoorder.OrderService;
import com.example.qtacoapp.tacoorder.TacoOrder;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private OrderService orderService;

    public PaymentService(OrderService orderService) {
        this.orderService = orderService;
    }


    private PaymentProvider getProvider(String name) {
        return PaymentProvider.allProvidersMap().get(name);
    }

    public PaymentProvider processPaymentInfo(PaymentProviderForm paymentProviderForm, TacoOrder currentOrder) {

        var paymentProvider = getProvider(paymentProviderForm.getName());

        if (paymentProviderForm.isStore()) {
            orderService.storePaymentInfo(paymentProvider, currentOrder);
        }

        return paymentProvider;
    }
}
