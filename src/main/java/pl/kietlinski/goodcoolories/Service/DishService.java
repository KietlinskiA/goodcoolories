package pl.kietlinski.goodcoolories.Service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Repository.DishRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class DishService {

    private List<Dish> dishList;
    private DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
        this.dishList = new ArrayList<>();
        initDatabase(dishRepository);
    }

    private void initDatabase(DishRepository dishRepository) {
        Dish dish1 = new Dish(1L,"Sałatka", "Sałatka do zrobienia", "http:\\salatka");
        Dish dish2 = new Dish(2L, "Makaron", "Makaron do zrobienia", "http:\\makaron");
        Dish dish3 = new Dish(3L, "Ryba", "Ryba do zrobienia", "http:\\ryba");
        dishRepository.save(dish1);
        dishRepository.save(dish2);
        dishRepository.save(dish3);
    }

    public List<Dish> getDishList() {
        return dishRepository.findAll();
    }

    public Dish getDishById(long dishId){
        if(dishRepository.findById(dishId).isPresent())
            return dishRepository.findById(dishId).get();
        return new Dish();
    }

}
