package com.example.ttacoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String streetNr;
    private String zipCode;
    private String city;
    private String country;
}
