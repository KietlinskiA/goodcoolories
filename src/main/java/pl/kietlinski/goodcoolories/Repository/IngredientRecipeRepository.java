package pl.kietlinski.goodcoolories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kietlinski.goodcoolories.Entity.IngredientRecipe;

@Repository
public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe, Long> {
}
