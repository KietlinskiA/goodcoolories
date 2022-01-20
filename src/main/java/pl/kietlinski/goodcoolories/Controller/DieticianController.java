package pl.kietlinski.goodcoolories.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.kietlinski.goodcoolories.Entity.*;
import pl.kietlinski.goodcoolories.Service.DieticianService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        return "redirect:/admin/login";
    }

    @GetMapping("/login")
    public String getStart(Model model) {
        model.addAttribute("error", dieticianService.getError());
        model.addAttribute("userPassword", "");
        return "dietician/login";
    }

    @RequestMapping("/checkPass")
    public String login(@RequestParam String userPassword) {
        if (dieticianService.checkPassword(userPassword)) {
            dieticianService.setError("");
            return "redirect:/admin/orders";
        } else {
            dieticianService.setError("Błędne hasło");
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        dieticianService.setError("");
        return "redirect:/admin/login";
    }

    @GetMapping("/orders")
    public String showOrders(Model model) {
        List<Order> orderList = dieticianService.getOrderListFromDb();
        model.addAttribute("orderList", orderList);
        return "dietician/orders";
    }

    @GetMapping("/order")
    public ModelAndView showOrder(@RequestParam long orderId, Model model) {
        Order order = dieticianService.getOrderById(orderId);
        User user = order.getUser();
        List<Dish> dishListFromOrder = dieticianService.getDishListFromOrder(order);
        List<Dish> dishListFromDb = dieticianService.getDishListFromDb();
        String visibility = dieticianService.getVisibility(dishListFromOrder, order);
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        model.addAttribute("dishListFromOrder", dishListFromOrder);
        model.addAttribute("dishListFromDb", dishListFromDb);
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
                                @RequestParam(required = false) double ingredient4proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient5checkbox,
                                @RequestParam(required = false) double ingredient5proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient6checkbox,
                                @RequestParam(required = false) double ingredient6proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient7checkbox,
                                @RequestParam(required = false) double ingredient7proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient8checkbox,
                                @RequestParam(required = false) double ingredient8proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient9checkbox,
                                @RequestParam(required = false) double ingredient9proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient10checkbox,
                                @RequestParam(required = false) double ingredient10proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient11checkbox,
                                @RequestParam(required = false) double ingredient11proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient12checkbox,
                                @RequestParam(required = false) double ingredient12proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient13checkbox,
                                @RequestParam(required = false) double ingredient13proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient14checkbox,
                                @RequestParam(required = false) double ingredient14proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient15checkbox,
                                @RequestParam(required = false) double ingredient15proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient16checkbox,
                                @RequestParam(required = false) double ingredient16proportion,
                                @RequestParam(required = false, defaultValue = "off") String ingredient17checkbox,
                                @RequestParam(required = false) double ingredient17proportion, HttpServletRequest httpServletRequest) {
        httpServletRequest.getParameterMap().forEach((key, values) -> System.out.println(key+":\t\t"+ Arrays.toString(values)));
        dieticianService.addDishToDb(
                new Dish(name, photo),
                new Recipe(levelOfDifficulty, preparationTime, description),
                List.of(ingredient1checkbox, ingredient2checkbox, ingredient3checkbox, ingredient4checkbox,
                        ingredient5checkbox, ingredient6checkbox, ingredient7checkbox, ingredient8checkbox,
                        ingredient9checkbox, ingredient10checkbox, ingredient11checkbox, ingredient12checkbox,
                        ingredient13checkbox, ingredient14checkbox, ingredient15checkbox, ingredient16checkbox,
                        ingredient17checkbox),
                List.of(ingredient1proportion, ingredient2proportion, ingredient3proportion, ingredient4proportion,
                        ingredient5proportion, ingredient6proportion, ingredient7proportion, ingredient8proportion,
                        ingredient9proportion, ingredient10proportion, ingredient11proportion, ingredient12proportion,
                        ingredient13proportion, ingredient14proportion, ingredient15proportion, ingredient16proportion,
                        ingredient17proportion)
        );

        return "redirect:/admin/add-recipe";
    }

    @RequestMapping("/change-status")
    public String changeStatus(@RequestParam long orderId) {
        dieticianService.setStatusAndSendEmailWithToken(orderId);
        return "redirect:/admin/order?orderId=" + orderId;
    }
}
