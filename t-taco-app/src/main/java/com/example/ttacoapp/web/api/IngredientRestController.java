package com.example.ttacoapp.web.api;

import com.example.ttacoapp.data.IngredientRepository;
import com.example.ttacoapp.domain.Ingredient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController sendet direkt Daten (keine TemplateEngine)
@RestController
@RequestMapping("/api/ingredients")
public class IngredientRestController {

    private final IngredientRepository ingredientRepository;

    public IngredientRestController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") String id) {

        var ingredient = ingredientRepository.findById(id);

        if (ingredient.isPresent()) {
            return ResponseEntity.ok(ingredient.get());
        }

        return ResponseEntity.badRequest().body("Ingredient " + id + " not found");
    }

    @GetMapping
    public List<Ingredient> findAll() {

        return ingredientRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Ingredient save(@RequestBody Ingredient ingredient) {

        return ingredientRepository.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") String id) {

        ingredientRepository.deleteById(id);
    }


}
