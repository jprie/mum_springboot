package com.example.ttacoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String street;
    private String streetNr;
    private String city;
    private String country;
}
