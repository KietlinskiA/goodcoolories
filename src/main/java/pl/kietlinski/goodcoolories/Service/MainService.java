package pl.kietlinski.goodcoolories.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Ingredient;
import pl.kietlinski.goodcoolories.Entity.Recipe;
import pl.kietlinski.goodcoolories.Repository.DishRepository;
import pl.kietlinski.goodcoolories.Repository.IngredientRepository;
import pl.kietlinski.goodcoolories.Repository.RecipeRepository;

@Service
@Data
public class MainService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private DishRepository dishRepository;

    @Autowired
    public MainService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, DishRepository dishRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishRepository;
    }

    public void initDatabase() {
        Ingredient ingredient1 = new Ingredient("Salata", 200, 20, 20, 20);
        Ingredient ingredient2 = new Ingredient("Makaron", 300, 30, 30, 30);
        Ingredient ingredient3 = new Ingredient("Ryba", 100, 10, 10, 10);
        Recipe recipe1 = new Recipe(true, 20, "Przepis na salatke");
        Recipe recipe2 = new Recipe(false, 30, "Przepis na makaron");
        Recipe recipe3 = new Recipe(true, 10, "Przepis na rybe");
        Dish dish1 = new Dish("Sałatka", "Sałatka do zrobienia", "http:\\salatka");
        Dish dish2 = new Dish( "Makaron", "Makaron do zrobienia", "http:\\makaron");
        Dish dish3 = new Dish( "Ryba", "Ryba do zrobienia", "http:\\ryba");

        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);

        ingredient1.setRecipe(recipe1);
        ingredient2.setRecipe(recipe1);
        ingredient3.setRecipe(recipe1);

        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);

        dish1.setRecipe(recipe1);
        dish2.setRecipe(recipe2);
        dish3.setRecipe(recipe3);

        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);

    }
}
