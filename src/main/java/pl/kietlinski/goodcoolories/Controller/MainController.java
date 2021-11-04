package pl.kietlinski.goodcoolories.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/contact")
    public String getContact() {
        return "contactForm";
    }

//    @GetMapping("/ingredients")
//    public List<Ingredient> getIngredients() {
//        return mainService.getIngredientRepository().findAll();
//    }
//
//    @GetMapping("/recipes")
//    public List<Recipe> getRecipes() {
//        return mainService.getRecipeRepository().findAll();
//    }


}
