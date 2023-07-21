package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(Config.class);

        var parrot = context.getBean(Parrot.class);

        System.out.println(parrot);

        var person = context.getBean(Person.class);
//        person.setName("Pauli");

        System.out.println(person);


    }
}