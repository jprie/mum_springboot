package com.example.ttacoapp.web;

import com.example.ttacoapp.domain.Ingredient;
import com.example.ttacoapp.domain.IngredientType;
import com.example.ttacoapp.domain.Taco;
import com.example.ttacoapp.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class TacoDesignController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        var ingredientsMap = Ingredient.allIngredients().stream()
                        .collect(Collectors.groupingBy(
                                ingredient -> ingredient.getType().toString().toLowerCase())
                        );

        model.addAllAttributes(ingredientsMap);
    }

    @ModelAttribute("tacoOrder")
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @ModelAttribute("taco") // Taco wird automatisch unter Name "taco" ins Model eingefügt
    public Taco taco() {
        return new Taco();
    }

    @GetMapping()
    public String showDesignForm(Model model) {

        return "designForm";
    }

    @PostMapping()
    public String processTaco(Taco taco,
                               @ModelAttribute TacoOrder tacoOrder){
        log.info("Taco: {}", taco);

        tacoOrder.addTaco(taco);

//        return "tacoOrderForm";
        return "redirect:/orders/current";
    }
}
