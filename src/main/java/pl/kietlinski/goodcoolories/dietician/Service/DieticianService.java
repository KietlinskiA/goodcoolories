package pl.kietlinski.goodcoolories.dietician.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Model.Dietician;
import pl.kietlinski.goodcoolories.Repository.DietRepository;
import pl.kietlinski.goodcoolories.Repository.DishRepository;
import pl.kietlinski.goodcoolories.Repository.OrderRepository;

@Service
@Data
public class DieticianService {

    private Dietician dietician;
    private int activeToken;
    private String error;
    private final OrderRepository orderRepository;
    private final DietRepository dietRepository;
    private final DishRepository dishRepository;

    @Autowired
    public DieticianService(OrderRepository orderRepository, DietRepository dietRepository, DishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        this.dishRepository = dishRepository;
        dietician = new Dietician(1L, 123);
        activeToken = 0;
        error = "";
    }
}
