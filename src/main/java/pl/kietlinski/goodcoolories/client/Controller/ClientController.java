package pl.kietlinski.goodcoolories.client.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.kietlinski.goodcoolories.Entity.Diet;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Order;
import pl.kietlinski.goodcoolories.Entity.OrderBuilder;
import pl.kietlinski.goodcoolories.client.Service.ClientService;

@Controller
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
    public ModelAndView getOrder() {
        return new ModelAndView("client/order", "order", new OrderBuilder().createOrder());
    }

    @PostMapping("/new-order")
    public String addNewOrder(@ModelAttribute Order newOrder) {
        clientService.getOrderRepository().save(newOrder);
        return "redirect:/thank-you";
    }

    @GetMapping("/find")
    public String getFind(Model model) {
        model.addAttribute("error", clientService.getErrorToken());
        return "client/find";
    }

    @RequestMapping("/find-token")
    public String findToken(@RequestParam String token) {
        if (!clientService.getDietRepository().existsByToken(token)) {
            clientService.setErrorToken("Nie znaleziono tokenu");
            return "redirect:/find";
        } else {
            clientService.setErrorToken("");
            return "redirect:/show-diet?token="+token;
        }
    }

    @GetMapping("/show-diet")
    public ModelAndView getShowDiet(@RequestParam String token) {
        Diet diet = clientService.getDietRepository().findByToken(token);
        clientService.setFoundDiet(diet);
        return new ModelAndView("client/showDiet", "diet", clientService.getFoundDiet());
    }

    @GetMapping("/show-dish")
    public ModelAndView getShowDish(@RequestParam long dishId) {
        Dish dishById = clientService.getDishRepository().getById(dishId);
        return new ModelAndView("client/showDish", "dish", dishById);
    }

}
