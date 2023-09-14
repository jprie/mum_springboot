package com.example.ttacoapp.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3, max = 25, message = "name must have at least 3 and at most 12 characters")
    private String name;

    @ManyToOne
    private TacoOrder order;
    @Size(min = 4, message = "choose at least 4 ingredients")
    @ManyToMany
    @JoinTable(
            name = "Taco_Ingredients",
            joinColumns = @JoinColumn(name = "taco_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    @Override
    public String toString() {
        return "Taco{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", order=" + (order == null ? "0" : order.getId()) +
                ", ingredients=" + ingredients +
                '}';
    }
}
