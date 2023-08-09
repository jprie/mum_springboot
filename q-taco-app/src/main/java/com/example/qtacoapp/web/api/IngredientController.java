package com.example.qtacoapp.web.api;

import com.example.qtacoapp.data.IngredientRepository;
import com.example.qtacoapp.domain.taco.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisNoOpBindingRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin("http://localhost:8080")
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public List<Ingredient> allIngredients() {

        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> ingredientById(@PathVariable String id) throws IngredientNotFoundException {

        return ResponseEntity.ok().body(
                ingredientRepository.findById(id).orElseThrow(
                        () -> new IngredientNotFoundException("Ingredient " + id + " not found")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {

        return ingredientRepository.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable String id) {

        ingredientRepository.deleteById(id);
    }

}
