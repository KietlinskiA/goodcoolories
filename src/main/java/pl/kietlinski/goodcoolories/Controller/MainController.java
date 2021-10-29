package pl.kietlinski.goodcoolories.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kietlinski.goodcoolories.Entity.CaloriesTable;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Ingredient;
import pl.kietlinski.goodcoolories.Entity.Recipe;
import pl.kietlinski.goodcoolories.Service.CaloriesTableService;
import pl.kietlinski.goodcoolories.Service.DishService;
import pl.kietlinski.goodcoolories.Service.IngredientService;
import pl.kietlinski.goodcoolories.Service.RecipeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/start")
public class MainController {

    private CaloriesTableService caloriesTableService;
    private DishService dishService;
    private IngredientService ingredientService;
    private RecipeService recipeService;

    @Autowired
    public MainController(CaloriesTableService caloriesTableService, DishService dishService, IngredientService ingredientService, RecipeService recipeService) {
        this.caloriesTableService = caloriesTableService;
        this.dishService = dishService;
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @Autowired

    @GetMapping("/caloriesTables")
    public List<CaloriesTable> getCaloriesTables() {
        return caloriesTableService.getCaloriesTableList();
    }

    @GetMapping("/dishes")
    public List<Dish> getDishes() {
        return dishService.getDishList();
    }

    @GetMapping("/ingredients")
    public List<Ingredient> getIngredients() {
        return ingredientService.getIngredientList();
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeService.getRecipeList();
    }

    @GetMapping("/all")
    public void getAll() {
        caloriesTableService.getCaloriesTableList().forEach(System.out::println);
        dishService.getDishList().forEach(System.out::println);
        ingredientService.getIngredientList().forEach(System.out::println);
        recipeService.getRecipeList().forEach(System.out::println);
    }

}
