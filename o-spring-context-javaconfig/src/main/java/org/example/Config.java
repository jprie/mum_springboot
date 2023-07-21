package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

    // nimm diese Bean vom Typ Parrot immer, wenn nicht genauer angegeben
    @Primary
    @Bean
    Parrot parrot() {
        var parrot = new Parrot();
        parrot.setName("Hugo");
        return parrot;
    }

    @Bean
    Parrot polly() {
        var parrot = new Parrot();
        parrot.setName("Polly");
        return parrot;
    }

    @Bean
    Person person(Parrot parrot) {
        var person = new Person(parrot);
        person.setName("Owner");
        return person;
    }
}
