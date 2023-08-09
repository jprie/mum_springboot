package com.example.qtacoapp.domain.payment;

import com.example.qtacoapp.domain.tacoorder.OrderService;
import com.example.qtacoapp.domain.tacoorder.TacoOrder;
import com.example.qtacoapp.web.checkout.PaymentProviderForm;
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
