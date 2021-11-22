package pl.kietlinski.goodcoolories.client.Service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.*;
import pl.kietlinski.goodcoolories.Repository.*;

@Service
@Data
public class ClientService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private DishRepository dishRepository;
    private OrderRepository orderRepository;
    private DietRepository dietRepository;

    private String errorToken;
    private Diet foundDiet;

    @Autowired
    public ClientService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, DishRepository dishService, OrderRepository orderRepository, DietRepository dietRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishService;
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        errorToken = " ";
        initDatabase();
    }

    public void initDatabase() {
        Ingredient ingredient1 = new Ingredient("Salata", 200, 20, 20, 20);
        Ingredient ingredient2 = new Ingredient("Makaron", 300, 30, 30, 30);
        Ingredient ingredient3 = new Ingredient("Ryba", 100, 10, 10, 10);
        Recipe recipe1 = new Recipe("łatwy", 20, "Przepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatke");
        Recipe recipe2 = new Recipe("trudny", 30, "Przepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaron");
        Recipe recipe3 = new Recipe("łatwy", 10, "Przepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybe");
        Dish dish1 = new Dish("Sałatka", "Sałatka do zrobienia", "http:\\salatka");
        Dish dish2 = new Dish("Makaron", "Makaron do zrobienia", "http:\\makaron");
        Dish dish3 = new Dish("Ryba", "Ryba do zrobienia", "http:\\ryba");
        Order order1 = new Order("Jan Kowal", "j.k@wp.pl", "111111111", "Kowalska 1",
                "11-111", "Kowalów", 25, 187, "male", "low", "up", 5,
                "Nie lubie salaty i pomidora");
        Order order2 = new Order("Ala Lis", "a.l@wp.pl", "222222222", "Lisia 2",
                "22-222", "Lisów", 45, 162, "female", "medium", "keep", 4,
                "Nie lubie twarogu i ryżu");
        Order order3 = new Order("Kamil Ul", "k.u@wp.pl", "333333333", "Ulowa 3",
                "33-333", "Ulów", 18, 173, "male", "high", "down", 5,
                "Nie lubie makaronu i sera żółtego");
        Diet diet1 = new Diet("111");
        Diet diet2 = new Diet("222");
        Diet diet3 = new Diet("333");

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

        dietRepository.save(diet1);
        dietRepository.save(diet2);
        dietRepository.save(diet3);

        dish1.setDiet(diet1);
        dish2.setDiet(diet1);
        dish3.setDiet(diet1);

        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);

        order1.setDiet(diet1);
        order2.setDiet(diet2);
        order3.setDiet(diet3);

        order1.setStatus("Zakończony");

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

    }
}
