package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(Config.class);

        var parrot = context.getBean(Parrot.class);

        var sameParrot = context.getBean(Parrot.class);

        if (parrot == sameParrot) {
            System.out.println("Parrots are the same");
        }

        var pollyParrot = context.getBean("polly");

        System.out.println(parrot);
        System.out.println(pollyParrot);

        var person = context.getBean(Person.class);

        System.out.println(person.getParrot().getName());

    }
}