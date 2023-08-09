package com.example.qtacoapp.data;

import com.example.qtacoapp.domain.taco.Ingredient;
import com.example.qtacoapp.domain.taco.IngredientType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void test_whenFindByName_thenReturnIngredient() {

        var shrimps = new Ingredient("Shrimps", "SHRP", IngredientType.PROTEIN, "0.8");
        testEntityManager.persist(shrimps);
        testEntityManager.flush();

        var foundIngredient = ingredientRepository.findById(shrimps.getId()).orElseThrow();

        assertEquals(shrimps, foundIngredient);
    }
}
