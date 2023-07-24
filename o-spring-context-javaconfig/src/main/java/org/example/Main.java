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

        Parrot pollyParrot = context.getBean("polly", Parrot.class);
        pollyParrot.setName("Burli");

        System.out.println("Bean parrot " + parrot);
        System.out.println("Bean polly " + pollyParrot);

        var person = context.getBean(Person.class);

        System.out.println("Name of person's parrot before " + person.getParrot().getName());
        person.setParrot(pollyParrot);

        System.out.println("Name of person's parrot after " + person.getParrot().getName());

    }
}