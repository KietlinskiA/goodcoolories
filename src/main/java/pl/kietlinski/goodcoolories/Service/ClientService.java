package pl.kietlinski.goodcoolories.Service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.Diet;
import pl.kietlinski.goodcoolories.Entity.Dish;
import pl.kietlinski.goodcoolories.Entity.Order;
import pl.kietlinski.goodcoolories.Entity.User;
import pl.kietlinski.goodcoolories.Repository.*;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class ClientService {

    private DishRepository dishRepository;
    private OrderRepository orderRepository;
    private DietRepository dietRepository;
    private UserRepository userRepository;

    private String errorToken;
    private LinkedList<Dish> dishList;

    @Autowired
    public ClientService(DishRepository dishRepository, OrderRepository orderRepository, DietRepository dietRepository, UserRepository userRepository) {
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
        this.errorToken = "";
    }

    public void saveOrder(User user, Order newOrder) {
        Optional<User> optionalUser = userRepository.findByPhone(user.getPhone());
        if(optionalUser.isEmpty()){
            userRepository.save(user);
            newOrder.setUser(user);
        } else {
            newOrder.setUser(optionalUser.get());
        }
        orderRepository.save(newOrder);
    }

    public boolean findTokenInDb(String token) {
        return dietRepository.existsByToken(token);
    }

    public void setDishList(String token) {
        Diet diet = dietRepository.findByToken(token);
        dishList = diet.getDishSet().stream().sorted().collect(Collectors.toCollection(LinkedList::new));
    }

    public Dish getDishById(long dishId) {
        return dishRepository.getById(dishId);
    }
}
