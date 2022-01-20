package pl.kietlinski.goodcoolories.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.kietlinski.goodcoolories.Entity.*;
import pl.kietlinski.goodcoolories.Service.DieticianService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
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
    public String login(Model model) {
        model.addAttribute("error", dieticianService.getError());
        model.addAttribute("password", "");
        return "dietician/login";
    }

    @GetMapping("/logout")
    public String logout() {
        dieticianService.setError("");
        return "redirect:/admin/login";
    }

    @GetMapping("/orders")
    public String showOrders(@RequestParam String password, Model model) {
        if(dieticianService.checkPassword(password)){
            dieticianService.setError("");
            List<Order> orderList = dieticianService.getOrderList();
            model.addAttribute("orderList", orderList);
            return "dietician/orders";
        } else {
            dieticianService.setError("Błędne hasło");
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/order")
    public ModelAndView showOrder(@RequestParam long orderId, Model model) {
        Order orderById = dieticianService.getOrderById(orderId);
        User user = orderById.getUser();
        dieticianService.setOrderDishList(orderById);
        List<Dish> orderDishList = dieticianService.getOrderDishList();
        List<Dish> allDishList = dieticianService.getAllDishList();
        String visibility = dieticianService.getVisibility(orderDishList, orderById);
        model.addAttribute("order", orderById);
        model.addAttribute("user", user);
        model.addAttribute("orderDishList", orderDishList);
        model.addAttribute("allDishList", allDishList);
        model.addAttribute("visibility", visibility);
        return new ModelAndView("dietician/order");
    }

    @RequestMapping("/delete-dish-from-diet")
    public String deleteDishFromDiet(@RequestParam long orderId, @RequestParam long dishId, @RequestParam long dietId) {
        dieticianService.deleteDishFromDiet(dishId, dietId);
        return "redirect:/admin/order?orderId=" + orderId;
    }

    @RequestMapping("/add-dish-to-diet")
    public String addDishToDiet(@RequestParam long orderId, @RequestParam long dishIdToAdd) {
        dieticianService.addDishToDiet(orderId, dishIdToAdd);
        return "redirect:/admin/order?orderId=" + orderId;
    }

    @GetMapping("/add-recipe")
    public String addRecipe(Model model) {
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

    @RequestMapping("/change-status")
    public String changeStatus(@RequestParam long orderId) {
        dieticianService.setStatus(orderId);
        try {
            dieticianService.sendEmailWithToken(orderId);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/order?orderId=" + orderId;
    }
}
