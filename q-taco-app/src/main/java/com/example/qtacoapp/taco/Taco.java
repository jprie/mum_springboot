package com.example.qtacoapp.taco;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Taco {

    @Size(min = 3)
    private String name;
    @Size(min = 4)
    private List<Ingredient> ingredients = new ArrayList<>();

    public Taco(String name) {
        this.name = name;
    }
}
