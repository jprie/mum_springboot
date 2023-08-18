package com.example.ttacoapp.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TacoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min=3, max=20)
    private String firstName;
    @Size(min=3, max=20)
    private String lastName;

    @Valid
    @Embedded
    private Address address;
    @Valid
    @Embedded
    private CreditCard creditCard;

    @NotEmpty
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {

        tacos.add(taco);
    }
}
