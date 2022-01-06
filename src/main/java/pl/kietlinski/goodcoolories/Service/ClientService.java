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
    private List<Dish> dishList;

    @Autowired
    public ClientService(DishRepository dishRepository, OrderRepository orderRepository, DietRepository dietRepository, UserRepository userRepository, IngredientRecipeRepository ingredientRecipeRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.errorToken = "";
        dishList = new ArrayList<>();
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

    public void setDishList(String token) {
        if(!dishList.isEmpty()){
            dishList.clear();
        }
        Diet diet = dietRepository.findByToken(token);
        List<Long> dishesIdByDietId = dishRepository.findDishesIdByDietId(diet.getDietId());
        for (Long dishId : dishesIdByDietId){
            Dish dish = dishRepository.getById(dishId);
            System.out.println(dish);
            dishList.add(dish);
        }
    }

    public Dish getDishById(long dishId) {
        return dishRepository.getById(dishId);
    }

    public List<String> getIngredientDescriptionList() {
        List<IngredientRecipe> ingredientRecipeList = ingredientRecipeRepository.findAll();
        List<String> stringList = new ArrayList<>();
        for(IngredientRecipe ingredientRecipe : ingredientRecipeList){
            Ingredient ingredient = ingredientRecipe.getIngredient();
            String weigthNumber = String.valueOf(ingredientRecipe.getProportions()*100);
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
        for(Ingredient ingredient : ingredientList){
            kcal += ingredient.getKcal();
            b += ingredient.getProtein();
            t += ingredient.getFat();
            w += ingredient.getCarbohydrates();
        }
        String kcalString = "Kcal: "+kcal;
        String bString = "B: "+b+"g";
        String tString = "T: "+t+"g";
        String wString = "W: "+w+"g";
        return List.of(kcalString, bString, tString, wString);
    }
}
