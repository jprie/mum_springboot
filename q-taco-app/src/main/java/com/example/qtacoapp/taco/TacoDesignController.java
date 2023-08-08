package com.example.qtacoapp.taco;

import com.example.qtacoapp.tacoorder.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class TacoDesignController {

    // model for design page

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        var ingredientsMap = Ingredient.ingredients
                .stream()
                .collect(Collectors.groupingBy(ingredient -> ingredient.getIngredientType().toString().toLowerCase() ));

        model.addAllAttributes(ingredientsMap);

        log.info("Ingredients: {}", model.getAttribute("bun"));

    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder tacoOrder() { return new TacoOrder(); }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String designTaco() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco,
                            Errors errors,
                            @ModelAttribute TacoOrder tacoOrder) {
      log.info("Processing taco: {}", taco);

      if (errors.hasErrors()) {
          errors.getAllErrors().forEach(error -> {
              log.error("Error validating taco: {}", error);
          });
          return null;
      }
      tacoOrder.addTaco(taco);
      return "redirect:/orders/current";

    }
}
