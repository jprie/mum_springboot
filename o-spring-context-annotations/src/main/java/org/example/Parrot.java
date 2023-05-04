package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Parrot {

    // Absichtlich ohne Konstruktor!!!
    private String name;

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Parrot{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
