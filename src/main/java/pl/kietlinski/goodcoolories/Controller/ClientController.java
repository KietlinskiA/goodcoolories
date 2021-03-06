package pl.kietlinski.goodcoolories.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Order;
import pl.kietlinski.goodcoolories.Entity.User;
import pl.kietlinski.goodcoolories.Model.UserBuilder;
import pl.kietlinski.goodcoolories.Model.OrderBuilder;
import pl.kietlinski.goodcoolories.Service.ClientService;

import java.util.List;

@Controller
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String get() {
        return "redirect:/hello";
    }

    @GetMapping("/hello")
    public String getHello() {
        return "client/hello";
    }

    @GetMapping("/thank-you")
    public String getThankYou() {
        return "client/thankYou";
    }

    @GetMapping("/order")
    public String getOrder(Model model) {
        model.addAttribute("flag", true);
        model.addAttribute("order", new OrderBuilder().createOrder());
        return "client/order";
    }

    @PostMapping("/new-order")
    public String addNewOrder(@RequestParam String name,
                              @RequestParam String eaddress,
                              @RequestParam String phone,
                              @RequestParam String street,
                              @RequestParam String zip,
                              @RequestParam String city,
                              @RequestParam int age,
                              @RequestParam int height,
                              @RequestParam String sex,
                              @RequestParam String activity,
                              @RequestParam String dietWish,
                              @RequestParam int dishCount,
                              @RequestParam String comment) {
        User user = new UserBuilder().setName(name).setEaddress(eaddress).setPhone(phone).setStreet(street).setZip(zip).setCity(city).setAge(age).setHeight(height).setSex(sex).createUser();
        Order newOrder = new Order(activity, dietWish, dishCount, comment, "Nowy");
        clientService.saveOrder(user, newOrder);
        return "redirect:/thank-you";
    }

    @GetMapping("/find")
    public String getFind(Model model) {
        model.addAttribute("error", clientService.getErrorToken());
        return "client/find";
    }

    @RequestMapping("/find-token")
    public String findToken(@RequestParam String token) {
        boolean tokenInDb = clientService.findTokenInDb(token);
        if (!tokenInDb) {
            clientService.setErrorToken("Nie znaleziono tokenu");
            return "redirect:/find";
        } else {
            clientService.setErrorToken("");
            return "redirect:/show-diet?token=" + token;
        }
    }

    @GetMapping("/show-diet")
    public String showDiet(@RequestParam String token, Model model) {
        model.addAttribute("dishList", clientService.getDishList(token));
        model.addAttribute("token", token);
        return "client/showDiet";
    }

    @GetMapping("/show-dish")
    public String showDish(@RequestParam long dishId, @RequestParam String token, Model model) {
        Dish dishById = clientService.getDishById(dishId);
        List<String> ingredientDescriptionList = clientService.getIngredientDescriptionList(dishById.getRecipe().getRecipeId());
        List<String> caloriesDescriptionList = clientService.getCaloriesDescriptionList(
                dishById.getRecipe().getRecipeId());
        model.addAttribute("dish", dishById);
        model.addAttribute("token", token);
        model.addAttribute("ingredientDescriptionList", ingredientDescriptionList);
        model.addAttribute("caloriesDescriptionList", caloriesDescriptionList);
        return "client/showDish";
    }

}
