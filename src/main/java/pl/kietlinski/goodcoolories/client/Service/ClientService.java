package pl.kietlinski.goodcoolories.client.Service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.*;
import pl.kietlinski.goodcoolories.Repository.*;

import java.util.Set;

@Service
@Data
public class ClientService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private IngredientInfoRepository ingredientInfoRepository;
    private DishRepository dishRepository;
    private OrderRepository orderRepository;
    private DietRepository dietRepository;

    private String errorToken;
    private Diet foundDiet;

    @Autowired
    public ClientService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, IngredientInfoRepository ingredientInfoRepository, DishRepository dishRepository, OrderRepository orderRepository, DietRepository dietRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientInfoRepository = ingredientInfoRepository;
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        this.errorToken = "";
        initDatabase();
    }

    public void initDatabase() {
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

        order1.setStatus("Zakończony");
        order1.setDiet(diet1);
        order2.setDiet(diet2);
        order3.setDiet(diet3);

        Dish dish1 = new Dish("Sałatka", "http:\\salatka");
        Dish dish2 = new Dish("Makaron", "http:\\makaron");
        Dish dish3 = new Dish("Ryba", "http:\\ryba");

        diet1.setDishSet(Set.of(dish1));
        diet2.setDishSet(Set.of(dish2));
        diet3.setDishSet(Set.of(dish3));

        dish1.setDietSet(Set.of(diet1));
        dish2.setDietSet(Set.of(diet2));
        dish3.setDietSet(Set.of(diet3));

        Recipe recipe1 = new Recipe("łatwy", 20, "Przepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatkePrzepis na salatke");
        Recipe recipe2 = new Recipe("trudny", 30, "Przepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaronPrzepis na makaron");
        Recipe recipe3 = new Recipe("łatwy", 10, "Przepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybePrzepis na rybe");

        recipe1.setDish(dish1);
        recipe2.setDish(dish2);
        recipe3.setDish(dish3);

        Ingredient ingredient1 = new Ingredient("Pomidor",0.9);
        Ingredient ingredient2 = new Ingredient("Makaron",1.5);
        Ingredient ingredient3 = new Ingredient("Ogórek",2.7);
        Ingredient ingredient4 = new Ingredient("Pomidor",0.9);
        Ingredient ingredient5 = new Ingredient("Makaron",1.5);
        Ingredient ingredient6 = new Ingredient("Ogórek",2.7);
        Ingredient ingredient7 = new Ingredient("Pomidor",0.9);
        Ingredient ingredient8 = new Ingredient("Makaron",1.5);
        Ingredient ingredient9 = new Ingredient("Ogórek",2.7);

        IngredientInfo ingredientInfo1 = new IngredientInfo( 200, 20, 20, 20);
        IngredientInfo ingredientInfo2 = new IngredientInfo( 300, 30, 30, 30);
        IngredientInfo ingredientInfo3 = new IngredientInfo( 100, 10, 10, 10);

        ingredient1.setIngredientInfo(ingredientInfo1);
        ingredient2.setIngredientInfo(ingredientInfo2);
        ingredient3.setIngredientInfo(ingredientInfo3);
        ingredient4.setIngredientInfo(ingredientInfo1);
        ingredient5.setIngredientInfo(ingredientInfo2);
        ingredient6.setIngredientInfo(ingredientInfo3);
        ingredient7.setIngredientInfo(ingredientInfo1);
        ingredient8.setIngredientInfo(ingredientInfo2);
        ingredient9.setIngredientInfo(ingredientInfo3);

        ingredient1.setRecipe(recipe1);
        ingredient2.setRecipe(recipe1);
        ingredient3.setRecipe(recipe1);
        ingredient4.setRecipe(recipe2);
        ingredient5.setRecipe(recipe2);
        ingredient6.setRecipe(recipe2);
        ingredient7.setRecipe(recipe3);
        ingredient8.setRecipe(recipe3);
        ingredient9.setRecipe(recipe3);

        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);

        dietRepository.save(diet1);
        dietRepository.save(diet2);
        dietRepository.save(diet3);

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        ingredientInfoRepository.save(ingredientInfo1);
        ingredientInfoRepository.save(ingredientInfo2);
        ingredientInfoRepository.save(ingredientInfo3);

        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);

        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);
        ingredientRepository.save(ingredient4);
        ingredientRepository.save(ingredient5);
        ingredientRepository.save(ingredient6);
        ingredientRepository.save(ingredient7);
        ingredientRepository.save(ingredient8);
        ingredientRepository.save(ingredient9);

    }
}
