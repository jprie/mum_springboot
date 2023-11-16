package com.example.ttacoapp.service;

import com.example.ttacoapp.data.TacoOrderRepository;
import com.example.ttacoapp.domain.Ingredient;
import com.example.ttacoapp.domain.Taco;
import com.example.ttacoapp.domain.TacoOrder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service // damit im Spring Container
public class TacoOrderService {

    private final TacoOrderRepository tacoOrderRepository;

    public TacoOrderService(TacoOrderRepository tacoOrderRepository) {
        this.tacoOrderRepository = tacoOrderRepository;
    }

    public TacoOrder save(TacoOrder tacoOrder) {
         return tacoOrderRepository.save(tacoOrder);
    }

    /**
     * record: Für Daten-Transport-Objekte (DTO)
     * Alle Attribute sind final
     * equals, hashCode, toString sind automatisch implementiert
     * GETTER: name(), price() statt getName(), getPrice()
     * @param name
     * @param price
     */
    public record TacoNameAndPrice(String name, BigDecimal price) {}

    public List<TacoNameAndPrice> calculateTacoPrices(TacoOrder tacoOrder) {

        return tacoOrder.getTacos().stream()
                .map(taco -> new TacoNameAndPrice(taco.getName(), calculatePrice(taco)))
                .toList();

    }

    private BigDecimal calculatePrice(Taco taco) {

        return taco.getIngredients().stream()
                .map(Ingredient::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    // TODO: summe berechnen, Service in injecten und verwenden

    public BigDecimal calculateSum(TacoOrder tacoOrder) {

        /*
        Berechnen Sie mit der Stream API die Gesamtsumme (Preise der Ingredients aller Tacos)
         */
        // .flatmap, .stream()
        return tacoOrder.getTacos().stream()
                .flatMap(taco -> taco.getIngredients().stream())
                .map(Ingredient::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }
}
