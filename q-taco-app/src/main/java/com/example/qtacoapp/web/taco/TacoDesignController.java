package com.example.qtacoapp.web.taco;

import com.example.qtacoapp.data.IngredientRepository;
import com.example.qtacoapp.domain.taco.Ingredient;
import com.example.qtacoapp.domain.taco.Taco;
import com.example.qtacoapp.domain.tacoorder.TacoOrder;
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
    private final IngredientRepository ingredientRepository;

    public TacoDesignController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    // model for design page

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        var ingredientsMap = ingredientRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(ingredient -> ingredient.getIngredientType().toString().toLowerCase() ));

        model.addAllAttributes(ingredientsMap);

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
          return null; // "design"
      }
      tacoOrder.addTaco(taco);
      return "redirect:/orders/current";

    }
}
