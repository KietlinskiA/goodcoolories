package pl.kietlinski.goodcoolories.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Ingredient;
import pl.kietlinski.goodcoolories.Repository.DishRepository;
import pl.kietlinski.goodcoolories.Repository.IngredientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class IngredientService {

    private List<Ingredient> ingredientList;
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(List<Ingredient> ingredientList, IngredientRepository ingredientRepository) {
        this.ingredientList = ingredientList;
        this.ingredientRepository = ingredientRepository;
        initDatabase();
    }

    private void initDatabase() {
        Ingredient ingredient1 = new Ingredient(1L, "Papryka", 200, 20, 20, 20);
        Ingredient ingredient2 = new Ingredient(2L, "Ogórek", 300, 30, 30, 30);
        Ingredient ingredient3 = new Ingredient(3L, "Brukselka", 100, 10, 10, 10);
        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);
    }

    public List<Ingredient> getIngredientList() {
        return ingredientRepository.findAll();
    }
}
