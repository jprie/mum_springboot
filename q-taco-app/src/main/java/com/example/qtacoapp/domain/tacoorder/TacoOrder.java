package com.example.qtacoapp.domain.tacoorder;

import com.example.qtacoapp.domain.shared.Price;
import com.example.qtacoapp.domain.taco.Taco;
import com.example.qtacoapp.domain.customer.Customer;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class TacoOrder {

    @Id
    private long id;
    private LocalDateTime timestamp = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Taco> tacos = new ArrayList<>();
    @Embedded
    private Price price;
    private String token = UUID.randomUUID().toString();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}
