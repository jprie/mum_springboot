package com.example.ttacoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacoOrder {

    private String firstName;
    private String lastName;

    private Address address;
    private CreditCard creditCard;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {

        tacos.add(taco);
    }
}
