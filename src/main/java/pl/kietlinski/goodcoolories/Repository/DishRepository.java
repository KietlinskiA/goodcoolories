package pl.kietlinski.goodcoolories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kietlinski.goodcoolories.Entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
