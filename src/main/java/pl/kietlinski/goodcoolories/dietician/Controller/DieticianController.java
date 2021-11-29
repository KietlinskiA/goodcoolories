package pl.kietlinski.goodcoolories.dietician.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.kietlinski.goodcoolories.Entity.Diet;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Order;
import pl.kietlinski.goodcoolories.Repository.OrderRepository;
import pl.kietlinski.goodcoolories.dietician.Service.DieticianService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DieticianController {

    private final DieticianService dieticianService;

    @Autowired
    public DieticianController(DieticianService dieticianService) {
        this.dieticianService = dieticianService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("dietician/login", "error", dieticianService.getError());
    }

    @GetMapping("/logout")
    public String logout() {
        dieticianService.setActiveToken(0);
        return "redirect:/admin/login";
    }

    @GetMapping("/orders")
    public ModelAndView showOrders(@RequestParam int activeToken, Model model) {
        if (activeToken == dieticianService.getDietician().getToken()) {
            dieticianService.setActiveToken(activeToken);
            dieticianService.setError("");
            model.addAttribute("orderList", dieticianService.getOrderRepository().findAll());
            model.addAttribute("activeToken", activeToken);
            return new ModelAndView("dietician/show_orders");
        } else {
            dieticianService.setError("Błędne dane");
            return new ModelAndView("redirect:/admin/login");
        }
    }

    @GetMapping("/show-order")
    public ModelAndView showOrder(@RequestParam long orderId, Model model) {
        Order orderById = dieticianService.getOrderRepository().getById(orderId);
        List<Dish> dishList = dieticianService.getDishRepository().findAll();
        model.addAttribute("order", orderById);
        model.addAttribute("dishList", dishList);
        model.addAttribute("activeToken", dieticianService.getActiveToken());
        return new ModelAndView("dietician/show_order");
    }

    @RequestMapping("/delete-dish-from-diet")
    public String deleteDishFromDiet(@RequestParam long dishId, @RequestParam long dietId) {
        Dish dish = dieticianService.getDishRepository().getById(dishId);
        dieticianService.getDishRepository().delete(dish);
        Diet diet = dieticianService.getDietRepository().getById(dietId);
        return "redirect:/admin/show-order?orderId="+diet.getOrder().getOrderId();
    }

//    @RequestMapping("/add-dish-to-diet")
//    public String addDishToDiet(@RequestParam long dishId, @RequestParam long dietId, Model model) {
//        Dish dish = dieticianService.getDishRepository().getById(dishId);
//        Diet diet = dieticianService.getDietRepository().getById(dietId);
//        dish.setDiet(diet);
//        dieticianService.getDishRepository().save(dish);
//        Order order = diet.getOrder();
//        return "redirect:/admin/show-order?orderId="+ order.getOrderId();
//    }

    @GetMapping("/panel")
    public ModelAndView showPanel(@RequestParam int activeToken) {
        if (activeToken == dieticianService.getActiveToken()) {
            return new ModelAndView("dietician/index", "activeToken", activeToken);
        } else {
            return new ModelAndView("redirect:/admin/login");
        }
    }


}
