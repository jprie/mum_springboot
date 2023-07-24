package com.example.qtacoapp.taco;

import com.example.qtacoapp.tacoorder.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class TacoDesignController {

    // model for design page

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        var ingredients = List.of(
                new Ingredient("White Bun", "WBUN", IngredientType.BUN),
                new Ingredient("Rye Bun", "RBUN", IngredientType.BUN),
                new Ingredient("Rucola", "RUCO", IngredientType.SALAD),
                new Ingredient("Lettuce", "LETT", IngredientType.SALAD),
                new Ingredient("Chicken", "CHIC", IngredientType.MEAT),
                new Ingredient("Burger", "BURG", IngredientType.MEAT),
                new Ingredient("Mozzarella", "MOZZ", IngredientType.CHEESE),
                new Ingredient("Gouda", "GOUD", IngredientType.CHEESE),
                new Ingredient("Red Sauce", "RSAU", IngredientType.SAUCE),
                new Ingredient("Sour creme", "SOUR", IngredientType.SAUCE),
                new Ingredient("Cucumber", "CUCU", IngredientType.VEGGIE),
                new Ingredient("Tomatoes", "TOMA", IngredientType.VEGGIE)
        );

        var ingredientsMap = ingredients
                .stream()
                .collect(Collectors.groupingBy(ingredient -> ingredient.getIngredientType().toString().toLowerCase() ));

        model.addAllAttributes(ingredientsMap);

        log.info("Ingredients: {}", model.getAttribute("bun"));

    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String designTaco() {
        return "design";
    }

    @PostMapping
    public void processTaco(@Valid Taco taco,
                            Errors errors,
                            @ModelAttribute TacoOrder tacoOrder) {
      log.info("Processing taco: {}", taco);
      tacoOrder.addTaco(taco);
      return "redirect-to"
    }
}
