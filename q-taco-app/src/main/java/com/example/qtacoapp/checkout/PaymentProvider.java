package com.example.qtacoapp.checkout;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record PaymentProvider(String name, String url) {

    static private List<PaymentProvider> providers = List.of(
            new PaymentProvider("MasterCard", "http://localhost:9090/masterCard"),
            new PaymentProvider( "Visa", "http://localhost:9090/visa")
    );

    public static Map<String, PaymentProvider> allProvidersMap() {

        return providers.stream().collect(Collectors.toMap(PaymentProvider::name, p -> p));
    }

    public static List<PaymentProvider> allProviders() {
        return providers;
    }
}
