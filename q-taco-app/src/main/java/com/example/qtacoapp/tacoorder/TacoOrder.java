package com.example.qtacoapp.tacoorder;

import com.example.qtacoapp.shared.Price;
import com.example.qtacoapp.taco.Taco;
import com.example.qtacoapp.customer.Customer;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
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
    @OneToMany
    private List<Taco> tacos = new ArrayList<>();
    @Embedded
    private Price price;
    private String token = UUID.randomUUID().toString();

    @ManyToOne
    private Customer customer;

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}
