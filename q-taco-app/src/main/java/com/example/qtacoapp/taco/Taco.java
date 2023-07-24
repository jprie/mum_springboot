package com.example.qtacoapp.taco;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Taco {

    private String name;
    private List<Ingredient> ingredients = new ArrayList<>();

    public Taco(String name) {
        this.name = name;
    }
}
