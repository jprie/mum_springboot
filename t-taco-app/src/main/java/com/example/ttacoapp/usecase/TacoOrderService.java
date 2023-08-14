package com.example.ttacoapp.usecase;

import com.example.ttacoapp.domain.Ingredient;
import com.example.ttacoapp.domain.Taco;
import com.example.ttacoapp.domain.TacoOrder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TacoOrderService {

    public void calculateTacoPrices(TacoOrder tacoOrder) {

        record TacoPrice(String name, BigDecimal price) {}

        tacoOrder.getTacos().stream()
                .map(taco -> new TacoPrice(taco.getName(), calculatePrice(taco)))
                .toList();

    }

    private BigDecimal calculatePrice(Taco taco) {
        return taco.getIngredients().stream()
                .map(Ingredient::getPrice)
                .reduce(BigDecimal::add).orElse(new BigDecimal(0));



    }
}
