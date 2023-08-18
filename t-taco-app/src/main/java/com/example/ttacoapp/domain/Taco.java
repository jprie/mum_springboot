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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min = 3, max = 25, message = "name must have at least 3 and at most 12 characters")
    private String name;
    @Size(min = 4, message = "choose at least 4 ingredients")
    @ManyToMany
    private List<Ingredient> ingredients;
}
