package com.example.qtacoapp.utility;

import com.example.qtacoapp.domain.customer.Address;
import com.example.qtacoapp.domain.customer.Customer;
import com.example.qtacoapp.domain.customer.EmailAddress;
import com.example.qtacoapp.domain.customer.PhoneNumber;
import com.example.qtacoapp.domain.taco.Ingredient;
import com.example.qtacoapp.domain.taco.IngredientType;
import com.example.qtacoapp.domain.taco.Taco;
import com.example.qtacoapp.domain.tacoorder.TacoOrder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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
                        allIngredients().get(0), // 1.5
                        allIngredients().get(1), // 1.5
                        allIngredients().get(2), // .3
                        allIngredients().get(3) // .3
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

    public static List<Ingredient> allIngredients() {

        return List.of(
                new Ingredient("Flour Tortilla", "FLTO", IngredientType.WRAP, "1.5"),
                new Ingredient("Corn Tortilla", "COTO", IngredientType.WRAP, "1.5"),
                new Ingredient("Rucola", "RUCO", IngredientType.SALAD, ".3"),
                new Ingredient("Lettuce", "LETT", IngredientType.SALAD, ".3"),
                new Ingredient("Chicken", "CHIC", IngredientType.PROTEIN, ".7"),
                new Ingredient("Ground Beaf", "BEAF", IngredientType.PROTEIN, ".7"),
                new Ingredient("Mozzarella", "MOZZ", IngredientType.CHEESE, ".4"),
                new Ingredient("Gouda", "GOUD", IngredientType.CHEESE, ".4"),
                new Ingredient("Red Sauce", "RSAU", IngredientType.SAUCE, ".2"),
                new Ingredient("Sour creme", "SOUR", IngredientType.SAUCE, ".2"),
                new Ingredient("Cucumber", "CUCU", IngredientType.VEGGIE, ".4"),
                new Ingredient("Tomatoes", "TOMA", IngredientType.VEGGIE, ".4")
        );
    }

    public static Map<String, List<Ingredient> > allIngredientsMap() {

        return allIngredients().stream()
                .collect(groupingBy(Ingredient::getId));
    }
}
