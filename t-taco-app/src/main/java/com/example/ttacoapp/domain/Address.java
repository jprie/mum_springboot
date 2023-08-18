package com.example.ttacoapp.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotEmpty
    private String street;
    @NotEmpty
    private String streetNr;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;
}
