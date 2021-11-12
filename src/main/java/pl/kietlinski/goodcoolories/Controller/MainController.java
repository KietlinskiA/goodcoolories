package pl.kietlinski.goodcoolories.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.kietlinski.goodcoolories.Entity.Order;
import pl.kietlinski.goodcoolories.Service.MainService;

@Controller
public class MainController {

    private MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
        mainService.initDatabase();
    }

    @GetMapping("/dish")
    public String getDish(Model model) {
        model.addAttribute("dish",mainService.getDishRepository().findById(1L));
        return "start";
    }

    @GetMapping("/order")
    public String getOrder() {
        return "order";
    }

    @GetMapping("/new-order")
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
                              @RequestParam int dishCount,
                              @RequestParam String comment
                              ) {
        Order newOrder = new Order(name, eaddress, phone, street, zip, city, age,
                height, sex, activity, dishCount, comment);
        mainService.getOrderRepository().save(newOrder);
        mainService.getOrderRepository().findAll().forEach(System.out::println);
        return "redirect:/order";
    }

}
