package com.example.qtacoapp.utility;

import com.example.qtacoapp.customer.Address;
import com.example.qtacoapp.customer.Customer;
import com.example.qtacoapp.customer.EmailAddress;
import com.example.qtacoapp.customer.PhoneNumber;
import com.example.qtacoapp.taco.Ingredient;
import com.example.qtacoapp.taco.Taco;
import com.example.qtacoapp.tacoorder.TacoOrder;
import org.springframework.mock.web.MockHttpSession;

import java.util.List;

public class Utility {

    public static TacoOrder createTacoOrder() {

        var tacoOrder = new TacoOrder();
        List<Taco> tacosList = List.of(getThetaco());
        tacoOrder.setTacos(tacosList);
        return tacoOrder;
    }

    private static Taco getThetaco() {
        var taco = new Taco("thetaco");
        taco.setIngredients(
                List.of(
                        Ingredient.ingredients.get(0), // 1.5
                        Ingredient.ingredients.get(1), // 1.5
                        Ingredient.ingredients.get(2), // .3
                        Ingredient.ingredients.get(3) // .3
                )
        );
        return taco;
    }

    public static Customer createCustomer() {
        return Customer.builder()
                .firstName("Johannes")
                .lastName("Priebsch")
                .address(new Address("Ettenreichgasse",
                        "26/18",
                        "Wien",
                        "VI",
                        1100,
                        "Austria")
                )
                .email(new EmailAddress("johannes@priebsch.at"))
                .phone(new PhoneNumber("+43", "6505656767"))
                .paymentProvider(null)
                .build();
    }
}
