package com.example.qtacoapp.tacoorder;

import com.example.qtacoapp.taco.Taco;
import com.example.qtacoapp.customer.Customer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {

    private long id;
    private LocalDateTime timestamp;
    private List<Taco> tacos = new ArrayList<>();

    private Customer customer;

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}
