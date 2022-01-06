package pl.kietlinski.goodcoolories.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Ingredient;
import pl.kietlinski.goodcoolories.Entity.IngredientRecipe;
import pl.kietlinski.goodcoolories.Entity.Recipe;
import pl.kietlinski.goodcoolories.Model.Dietician;
import pl.kietlinski.goodcoolories.Repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class DieticianService {

    private final OrderRepository orderRepository;
    private final DietRepository dietRepository;
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientRecipeRepository ingredientRecipeRepository;
    private final RecipeRepository recipeRepository;
    private Dietician dietician;
    private int activeToken;
    private String error;
    private List<Ingredient> ingredientList;

    @Autowired
    public DieticianService(OrderRepository orderRepository, DietRepository dietRepository, DishRepository dishRepository, IngredientRepository ingredientRepository, IngredientRecipeRepository ingredientRecipeRepository, RecipeRepository recipeRepository) {
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.recipeRepository = recipeRepository;
        dietician = new Dietician(1L, 123);
        activeToken = 0;
        error = "";
        ingredientList = ingredientRepository.findAll();
    }


    public void addDishToDb(Dish dish, Recipe recipe, List<String> checkboxs, List<Double> proportions) {
        List<IngredientRecipe> ingredientRecipesToAdd = new ArrayList<>();
        List<Ingredient> ingredientListToAdd = new ArrayList<>();;

        recipe.setDish(dish);
        for(int i=0; i<4; i++) {
            if(checkboxs.get(i).equals("on")){
                IngredientRecipe ingredientRecipe = new IngredientRecipe(proportions.get(i));
                ingredientRecipe.setRecipe(recipe);
                ingredientRecipe.setIngredient(ingredientList.get(i));
                ingredientRecipesToAdd.add(ingredientRecipe);
                ingredientListToAdd.add(ingredientList.get(i));
            }
        }
        dishRepository.save(dish);
        for(Ingredient ingredient : ingredientListToAdd){
            ingredientRepository.save(ingredient);
        };
        recipeRepository.save(recipe);
        for(IngredientRecipe ingredientRecipe : ingredientRecipesToAdd){
            ingredientRecipeRepository.save(ingredientRecipe);
        };
    }
}
