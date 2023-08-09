package com.example.qtacoapp.domain.customer;

public record Address(
        String streetName,
        String streetNr,
        String city,
        String state,
        int zipCode,
        String country) {
}
