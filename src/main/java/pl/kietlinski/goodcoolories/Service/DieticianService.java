package pl.kietlinski.goodcoolories.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.*;
import pl.kietlinski.goodcoolories.Model.Dietician;
import pl.kietlinski.goodcoolories.Repository.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Data
public class DieticianService {

    private final OrderRepository orderRepository;
    private final DietRepository dietRepository;
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientRecipeRepository ingredientRecipeRepository;
    private final RecipeRepository recipeRepository;
    private EmailService emailService;
    private Dietician dietician;
    private String error;
    private List<Ingredient> ingredientList;
    private List<Dish> orderDishList;
    private static String visibility;
    private static String VISIBLE = "visible";
    private static String HIDDEN = "hidden";
    @Value("${spring.mail.username}")
    private String springMailUsername;

    @Autowired
    public DieticianService(OrderRepository orderRepository, DietRepository dietRepository, DishRepository dishRepository, IngredientRepository ingredientRepository, IngredientRecipeRepository ingredientRecipeRepository, RecipeRepository recipeRepository ,EmailService emailService) {
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.recipeRepository = recipeRepository;
        this.emailService = emailService;
        dietician = new Dietician(1L, 123);
        error = "";
        ingredientList = ingredientRepository.findAll();
        orderDishList = new ArrayList<>();
        visibility = "";
    }


    public void addDishToDb(Dish dish, Recipe recipe, List<String> checkboxs, List<Double> proportions) {
        List<IngredientRecipe> ingredientRecipesToAdd = new ArrayList<>();
        List<Ingredient> ingredientListToAdd = new ArrayList<>();
        recipe.setDish(dish);
        for (int i = 0; i < 4; i++) {
            if (checkboxs.get(i).equals("on")) {
                IngredientRecipe ingredientRecipe = new IngredientRecipe(proportions.get(i));
                ingredientRecipe.setRecipe(recipe);
                ingredientRecipe.setIngredient(ingredientList.get(i));
                ingredientRecipesToAdd.add(ingredientRecipe);
                ingredientListToAdd.add(ingredientList.get(i));
            }
        }
        dishRepository.save(dish);
        for (Ingredient ingredient : ingredientListToAdd) {
            ingredientRepository.save(ingredient);
        }
        recipeRepository.save(recipe);
        for (IngredientRecipe ingredientRecipe : ingredientRecipesToAdd) {
            ingredientRecipeRepository.save(ingredientRecipe);
        }
    }

    public Order getOrderById(long orderId) {
        return orderRepository.getById(orderId);
    }

    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    public void setOrderDishList(Order orderById) {
        if (!orderDishList.isEmpty()) {
            orderDishList.clear();
        }
        Diet diet = orderById.getDiet();
        List<Long> dishesIdByDietId = dishRepository.findDishesIdByDietId(diet.getDietId());
        for (Long dishId : dishesIdByDietId) {
            Dish dish = dishRepository.getById(dishId);
            System.out.println(dish);
            orderDishList.add(dish);
        }
    }

    public List<Dish> getAllDishList() {
        return dishRepository.findAll();
    }

    public String getVisibility(List<Dish> orderDishList, Order orderById) {
        if (orderDishList.size() <= orderById.getDishCount()-1) {
            visibility = VISIBLE;
        } else {
            visibility = HIDDEN;
        }
        return visibility;
    }

    public void deleteDishFromDiet(long dishId, long dietId) {
        Dish dish = dishRepository.getById(dishId);
        Diet diet = dietRepository.getById(dietId);
        diet.getDishSet().remove(dish);
        dietRepository.save(diet);
    }

    public void addDishToDiet(long orderId, long dishIdToAdd) {
        Order order = orderRepository.getById(orderId);
        Set<Dish> orderDishSet = order.getDiet().getDishSet();
        System.out.println(orderDishSet.size());
        System.out.println(orderDishSet);
        if (orderDishSet.stream().noneMatch(dish -> dish.getDishId() == dishIdToAdd)) {
            Dish dish = dishRepository.getById(dishIdToAdd);
            Diet diet = order.getDiet();
            diet.getDishSet().add(dish);
            dietRepository.save(diet);
        }
    }

    public void setStatus(long orderId) {
        Order order = orderRepository.getById(orderId);
        if(order.getStatus().equals("Nowy")){
            order.setStatus("Zakończony");
        } else {
            order.setStatus("Nowy");
        }
        orderRepository.save(order);
    }

    public boolean checkPassword(String password) {
        String dieticianToken = String.valueOf(dietician.getToken());
        return dieticianToken.equals(password);
    }

    public void sendEmailWithToken(long orderId) throws MessagingException, UnsupportedEncodingException {
        Order order = orderRepository.getById(orderId);
        dietRepository.fin
        setNewToken();
        String eaddress = order.getUser().getEaddress();
        String token = order.getDiet().getToken();
        String token = "1234";
        String template = emailService.getTemplate(token);
        emailService.sendEmail(springMailUsername, eaddress, "Hej - Twoja dieta jest gotowa!", template);
    }

    public void setNewToken(long dietId) {
        return "Hello";
    }
}
