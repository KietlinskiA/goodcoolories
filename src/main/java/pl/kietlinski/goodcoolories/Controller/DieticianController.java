package pl.kietlinski.goodcoolories.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.kietlinski.goodcoolories.Entity.Diet;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Order;
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
        model.addAttribute("orderList", dieticianService.getOrderRepository().findAll());
        return new ModelAndView("dietician/orders");
    }

    @GetMapping("/order")
    public ModelAndView showOrder(@RequestParam long orderId, Model model) {
        Order orderById = dieticianService.getOrderRepository().getById(orderId);
        List<Dish> dishList = dieticianService.getDishRepository().findAll();
        model.addAttribute("order", orderById);
        model.addAttribute("dishList", dishList);
        model.addAttribute("activeToken", dieticianService.getActiveToken());
        return new ModelAndView("dietician/order");
    }

    @RequestMapping("/delete-dish-from-diet")
    public String deleteDishFromDiet(@RequestParam long dishId, @RequestParam long dietId) {
        Dish dish = dieticianService.getDishRepository().getById(dishId);
        Diet diet = dieticianService.getDietRepository().getById(dietId);
        diet.getDishSet().remove(dish);
        dieticianService.getDietRepository().save(diet);
        return "redirect:/admin/order?orderId=" + diet.getOrder().getOrderId();
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
    public ModelAndView showRecipes() {
        return new ModelAndView("dietician/addRecipe", "newDish", new Dish());
    }

    @RequestMapping("/add-dish-to-base")
    public String addDishToBase(@RequestBody Dish dish) {
        dieticianService.getDishRepository().save(dish);
        return "redirect:/admin/recipes";
    }



}
