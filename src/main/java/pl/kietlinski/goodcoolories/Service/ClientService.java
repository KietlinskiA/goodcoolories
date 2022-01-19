package pl.kietlinski.goodcoolories.Service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.*;
import pl.kietlinski.goodcoolories.Repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ClientService {

    private DishRepository dishRepository;
    private OrderRepository orderRepository;
    private DietRepository dietRepository;
    private UserRepository userRepository;
    private IngredientRecipeRepository ingredientRecipeRepository;
    private IngredientRepository ingredientRepository;

    private String errorToken;

    @Autowired
    public ClientService(DishRepository dishRepository, OrderRepository orderRepository, DietRepository dietRepository, UserRepository userRepository, IngredientRecipeRepository ingredientRecipeRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.errorToken = "";
    }

    public void saveOrder(User user, Order newOrder) {
        Optional<User> optionalUser = userRepository.findByPhone(user.getPhone());
        if(optionalUser.isEmpty()){
            userRepository.save(user);
            newOrder.setUser(user);
        } else {
            newOrder.setUser(optionalUser.get());
        }
        orderRepository.save(newOrder);
    }

    public boolean findTokenInDb(String token) {
        return dietRepository.existsByToken(token);
    }

    public List<Dish> getDishList(String token) {
        List<Dish> dishList = new ArrayList<>();
        Diet diet = dietRepository.findByToken(token);
        List<Long> dishesIdByDietId = dishRepository.findDishesIdByDietId(diet.getDietId());
        for (Long dishId : dishesIdByDietId){
            Dish dish = dishRepository.getById(dishId);
            dishList.add(dish);
        }
        return dishList;
    }

    public Dish getDishById(long dishId) {
        return dishRepository.getById(dishId);
    }

    public List<String> getIngredientDescriptionList(long recipeId) {
        List<IngredientRecipe> ingredientRecipeList = ingredientRecipeRepository.getIngredientRecipesByRecipeId(recipeId);
        List<String> stringList = new ArrayList<>();
        for(IngredientRecipe ingredientRecipe : ingredientRecipeList){
            Ingredient ingredient = ingredientRecipe.getIngredient();
            String weigthNumber = String.valueOf((int)(ingredientRecipe.getProportions()*100));
            String string = weigthNumber + "g " + ingredient.getName();
            stringList.add(string);
        }
        return stringList;
    }

    public List<String> getCaloriesDescriptionList(long recipeId) {
        List<IngredientRecipe> ingredientRecipeList = ingredientRecipeRepository.getIngredientRecipesByRecipeId(recipeId);
        List<Ingredient> ingredientList = new ArrayList<>();
        for(IngredientRecipe ingredientRecipe : ingredientRecipeList) {
            ingredientList.add(ingredientRecipe.getIngredient());
        }
        int kcal = 0;
        double b=0, t=0, w=0;
        for(IngredientRecipe ingredientRecipe : ingredientRecipeList){
            Ingredient ingredient = ingredientRecipe.getIngredient();
            double proportions = ingredientRecipe.getProportions();
            kcal += ingredient.getKcal() * proportions;
            b += ingredient.getProtein() * proportions;
            t += ingredient.getFat() * proportions;
            w += ingredient.getCarbohydrates() * proportions;
        }
        String kcalString = "Kcal: "+kcal;
        String bString = "B: "+(int)b+"g";
        String tString = "T: "+(int)t+"g";
        String wString = "W: "+(int)w+"g";
        return List.of(kcalString, bString, tString, wString);
    }
}
