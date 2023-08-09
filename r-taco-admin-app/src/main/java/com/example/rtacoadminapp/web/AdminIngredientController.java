package com.example.rtacoadminapp.web;

import com.example.rtacoadminapp.domain.Ingredient;
import com.example.rtacoadminapp.domain.IngredientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/ingredients")
public class AdminIngredientController {

    private IngredientService ingredientService;

    public AdminIngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @GetMapping
    public String showAllIngredients(Model model) {

        var ingredients = ingredientService.allIngredients();
        var ingredientTypes = ingredients.stream().map(Ingredient::getIngredientType).distinct().toList();

        model.addAttribute("ingredients", ingredients);
        model.addAttribute("ingredientTypes", ingredientTypes);

        return "ingredients";
    }

    @ModelAttribute
    public Ingredient ingredient() {
        return new Ingredient();
    }

    @ModelAttribute
    public void addIngredientTypesToModel(Model model) {

        var ingredientTypes = ingredientService.allIngredients().stream().map(Ingredient::getIngredientType).distinct().toList();

        model.addAttribute("ingredientTypes", ingredientTypes);

    }

    @GetMapping("/new")
    public String ingredientForm(Model model) {


        return "ingredientForm";
    }

    @PostMapping
    public String saveIngredient(@Valid Ingredient ingredient, Errors errors) {

        if (errors.hasErrors()) {
            log.error("Errors: {}", errors.getAllErrors());
            return "ingredientForm";
        }
        ingredientService.saveIngredient(ingredient);
        return "redirect:/admin/ingredients";
    }


}
