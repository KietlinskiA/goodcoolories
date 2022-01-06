package pl.kietlinski.goodcoolories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kietlinski.goodcoolories.Entity.IngredientRecipe;

import java.util.List;

@Repository
public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe, Long> {

    @Query(value = "SELECT * FROM ingredients_recipes WHERE recipe_recipe_id = :recipeId", nativeQuery = true)
    List<IngredientRecipe> getIngredientRecipesByRecipeId(@Param("recipeId") Long recipeId);

}
