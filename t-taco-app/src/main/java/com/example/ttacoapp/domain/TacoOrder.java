package com.example.ttacoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacoOrder {

    private String firstName;
    private String lastName;

    private Address address;
    private CreditCard creditCard;


}
