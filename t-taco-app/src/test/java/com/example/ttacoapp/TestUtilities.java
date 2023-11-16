package com.example.ttacoapp;

import com.example.ttacoapp.domain.*;

import java.math.BigDecimal;
import java.util.List;

public class TestUtilities {
    public static Taco tacoForm() {
        var taco = new Taco(0L, "my taco", null, createIngredients());
        return taco;
    }

    public static List<Ingredient> createIngredients() {
        return List.of(
                new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP, new BigDecimal("0.5")),
                new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP, new BigDecimal("0.6")),
                new Ingredient("TMTO", "Tomatoes", IngredientType.VEGGIE, new BigDecimal("0.2")),
                new Ingredient("LETT", "Lettuce", IngredientType.VEGGIE, new BigDecimal("0.3"))
        );
    }

    public static TacoOrder tacoOrderFromForm() {

        var tacoOrder = new TacoOrder(0l, "Johannes", "Priebsch", address(), creditCard(), List.of());
        return tacoOrder;
    }

    private static CreditCard creditCard() {
        return new CreditCard("Hans Maier", "2221004368846623", "12/23", "123");
    }

    private static Address address() {

        return new Address("Franz-Jonas-Platz", "1", "1210", "Wien", "Austria");
    }

    public static TacoOrder tacoOrderEntity() {
        var tacoOrder = tacoOrderFromForm();
        tacoOrder.setTacos(List.of(tacoForm()));
        return tacoOrder;
    }

}
