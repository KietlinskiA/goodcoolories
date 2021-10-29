package pl.kietlinski.goodcoolories.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Recipe;
import pl.kietlinski.goodcoolories.Repository.DishRepository;
import pl.kietlinski.goodcoolories.Repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RecipeService {

    private List<Recipe> recipeList;
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(List<Recipe> recipeList, RecipeRepository recipeRepository) {
        this.recipeList = recipeList;
        this.recipeRepository = recipeRepository;
        initDatabase();
    }

    private void initDatabase() {
        Recipe recipe1 = new Recipe(1L, true, 20, "Przygotuj garnek");
        Recipe recipe2 = new Recipe(2L, false, 30, "Ugotuj ryż");
        Recipe recipe3 = new Recipe(3L, true, 10, "Podaj na talerz");
        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);
    }

    public List<Recipe> getRecipeList() {
        return recipeRepository.findAll();
    }
}
