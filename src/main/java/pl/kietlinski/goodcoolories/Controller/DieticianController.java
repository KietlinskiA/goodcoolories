package pl.kietlinski.goodcoolories.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.kietlinski.goodcoolories.Entity.*;
import pl.kietlinski.goodcoolories.Service.DieticianService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DieticianController {

    private final DieticianService dieticianService;

    @Autowired
    public DieticianController(DieticianService dieticianService) {
        this.dieticianService = dieticianService;
    }

    @GetMapping
    public String get() {
        return "redirect:admin/login";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("dietician/login", "error", dieticianService.getError());
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/admin/login";
    }

    @GetMapping("/orders")
    public ModelAndView showOrders(Model model) {
        dieticianService.setError("");
        List<Order> orderList = dieticianService.getOrderList();
        model.addAttribute("orderList", orderList);
        return new ModelAndView("dietician/orders");
    }

    @GetMapping("/order")
    public ModelAndView showOrder(@RequestParam long orderId, Model model) {
        Order orderById = dieticianService.getOrderById(orderId);
        User user = orderById.getUser();
        List<Dish> dishList = dieticianService.getDishList();
        model.addAttribute("order", orderById);
        model.addAttribute("user", user);
        model.addAttribute("dishList", dishList);
        return new ModelAndView("dietician/order");
    }

    @RequestMapping("/delete-dish-from-diet")
    public String deleteDishFromDiet(@RequestParam long orderId, @RequestParam long dishId, @RequestParam long dietId) {
        Dish dish = dieticianService.getDishRepository().getById(dishId);
        Diet diet = dieticianService.getDietRepository().getById(dietId);
        diet.getDishSet().remove(dish);
        dieticianService.getDietRepository().save(diet);
        return "redirect:/admin/order?orderId=" + orderId;
    }

    @RequestMapping("/add-dish-to-diet")
    public String addDishToDiet(@RequestParam long orderId, @RequestParam long dishIdToAdd) {
        Order order = dieticianService.getOrderRepository().getById(orderId);
        Dish dish = dieticianService.getDishRepository().getById(dishIdToAdd);
        Diet diet = order.getDiet();
        diet.getDishSet().add(dish);
        dieticianService.getDietRepository().save(diet);
        return "redirect:/admin/order?orderId=" + orderId;
    }

    @GetMapping("/add-recipe")
    public String showRecipes(Model model) {
        List<Ingredient> ingredientList = dieticianService.getIngredientList();
        model.addAttribute("ingredientList", ingredientList);
        model.addAttribute("newDish", new Dish());
        return "dietician/addRecipe";
    }

    @PostMapping("/add-dish-to-base")
    public String addDishToBase(@RequestParam String name,
                                @RequestParam String photo,
                                @RequestParam String levelOfDifficulty,
                                @RequestParam int preparationTime,
                                @RequestParam String description,
                                @RequestParam(required = false, defaultValue = "off") String ingredient1checkbox,
                                @RequestParam(required = false) double ingredient1proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient2checkbox,
                                @RequestParam(required = false) double ingredient2proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient3checkbox,
                                @RequestParam(required = false) double ingredient3proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient4checkbox,
                                @RequestParam(required = false) double ingredient4proportion) {
        dieticianService.addDishToDb(
                new Dish(name, photo),
                new Recipe(levelOfDifficulty, preparationTime, description),
                List.of(ingredient1checkbox, ingredient2checkbox, ingredient3checkbox, ingredient4checkbox),
                List.of(ingredient1proportion, ingredient2proportion, ingredient3proportion, ingredient4proportion)
        );
        return "redirect:/admin/add-recipe";
    }



}
