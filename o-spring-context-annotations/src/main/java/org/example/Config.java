package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "org.example")
public class Config {

    @Bean
    public String name() {
        return "Pauli from Bean";
    }

    @Bean
    public String parrotName() {
        return "Polly";
    }
}
