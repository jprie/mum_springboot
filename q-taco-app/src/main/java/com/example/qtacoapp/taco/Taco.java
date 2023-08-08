package com.example.qtacoapp.taco;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Taco {

    @Id
    private long id;
    @NotBlank(message = "no name given")
    @Size(min = 3, message = "name must be 3 characters or longer")
    private String name;
    @Size(min = 4, message = "not enough ingredients")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    public Taco(String name) {
        this.name = name;
    }
}
