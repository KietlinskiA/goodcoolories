package pl.kietlinski.goodcoolories.Service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.Diet;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Order;
import pl.kietlinski.goodcoolories.Repository.*;

import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
@Data
public class ClientService {

    private RecipeRepository recipeRepository;
    private IngredientRecipeRepository ingredientRecipeRepository;
    private IngredientRepository ingredientRepository;
    private DishRepository dishRepository;
    private OrderRepository orderRepository;
    private DietRepository dietRepository;

    private String errorToken;
    private LinkedList<Dish> dishList;

    @Autowired
    public ClientService(RecipeRepository recipeRepository, IngredientRecipeRepository ingredientRecipeRepository, IngredientRepository ingredientRepository, DishRepository dishRepository, OrderRepository orderRepository, DietRepository dietRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        this.errorToken = "";
    }

    public void saveOrder(Order newOrder) {
        newOrder.setStatus("Nowy");
        orderRepository.save(newOrder);
    }

    public boolean findTokenInDb(String token) {
        return dietRepository.existsByToken(token);
    }

    public void setDishList(String token) {
        Diet diet = dietRepository.findByToken(token);
        dishList = diet.getDishSet().stream().sorted().collect(Collectors.toCollection(LinkedList::new));
    }

    public Dish getDishById(long dishId) {
        return dishRepository.getById(dishId);
    }
}
