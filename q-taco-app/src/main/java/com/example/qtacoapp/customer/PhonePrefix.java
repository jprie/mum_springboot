package com.example.qtacoapp.customer;

import java.util.List;

public record PhonePrefix(String country, String number) {

    public static List<PhonePrefix> allPrefixes() {

        return List.of(
           new PhonePrefix("AT", "+43"),
           new PhonePrefix("DE", "+49"),
           new PhonePrefix("IT", "+39"),
           new PhonePrefix("ES", "+34")
        );
    }
}
