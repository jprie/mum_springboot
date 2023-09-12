package com.example.ttacoapp.data;

import com.example.ttacoapp.domain.Ingredient;
import com.example.ttacoapp.domain.IngredientType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepositoryJdbc implements IngredientRepository {

    private static final String SELECT_FROM_INGREDIENT = "SELECT * FROM Ingredient";
    private static final String SELECT_FROM_INGREDIENT_BY_ID = "SELECT * FROM Ingredient WHERE id=?";
    private static final String INSERT_INTO_INGREDIENT = "INSERT INTO Ingredient(id, name, type, price) values ( ?,?,?,? )";

    private static final RowMapper<Ingredient> rowMapper = ((rs, rowNum) -> {
       Ingredient ingredient = new Ingredient();
       ingredient.setId(rs.getString("id"));
       ingredient.setName(rs.getString("name"));
       ingredient.setType(IngredientType.valueOf(rs.getString("type")));
       ingredient.setPrice(rs.getBigDecimal("price"));
       return ingredient;
    });

    private final JdbcTemplate jdbcTemplate;

    public IngredientRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query(SELECT_FROM_INGREDIENT, rowMapper);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_FROM_INGREDIENT_BY_ID, rowMapper, id));
    }

    @Override
    public void save(Ingredient ingredient) {
        jdbcTemplate.update(INSERT_INTO_INGREDIENT,
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().name(),
                ingredient.getPrice()
        );
    }

    @Override
    public void saveAll(List<Ingredient> ingredients) {
        for (var ingredient : ingredients) {
            jdbcTemplate.update(INSERT_INTO_INGREDIENT,
                    ingredient.getId(),
                    ingredient.getName(),
                    ingredient.getType().name(),
                    ingredient.getPrice()
            );
        }
    }
}
