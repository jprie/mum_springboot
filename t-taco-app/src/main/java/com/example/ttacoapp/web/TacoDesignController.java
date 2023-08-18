package com.example.ttacoapp.web;

import com.example.ttacoapp.data.IngredientRepository;
import com.example.ttacoapp.domain.Taco;
import com.example.ttacoapp.domain.TacoOrder;
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

    private IngredientRepository ingredientRepository;

    public TacoDesignController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        var ingredientsMap = ingredientRepository.findAll().stream()
                        .collect(Collectors.groupingBy(
                                ingredient -> ingredient.getType().toString().toLowerCase())
                        );

        // model.addAttribute(key1, value1), model.addAttribute(key2, value2), ...
        model.addAllAttributes(ingredientsMap);
    }

    @ModelAttribute("tacoOrder") // model.addAttribute("tacoOrder", new TacoOrder())
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @ModelAttribute("taco") // Taco wird automatisch unter Name "taco" ins Model eingefügt
    public Taco taco() {
        return new Taco();
    }

    @GetMapping()
    public String showDesignForm(Model model) {

        return "designForm"; // ruft TemplateEngine mit template und model auf
    }

    @PostMapping()
    public String processTaco(@Valid Taco taco,
                               Errors errors,
                               @ModelAttribute TacoOrder tacoOrder){ // holt TacoOrder aus dem Model

        if (errors.hasErrors()) {
            for (var error : errors.getAllErrors()) {
                log.error("Error {}", error);
            }
            return "designForm"; // errors werden automatisch an die TemplateEngine übergeben
        }

        log.info("Taco: {}", taco);
        tacoOrder.addTaco(taco);

//        return "tacoOrderForm";
        return "redirect:/orders/current";
    }
}
