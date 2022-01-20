package pl.kietlinski.goodcoolories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kietlinski.goodcoolories.Entity.Dish;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query(value = "SELECT dish_set_dish_id FROM diets_dish_set WHERE diet_set_diet_id = :dietId", nativeQuery = true)
    List<Long> findDishesIdByDietId(@Param("dietId") Long dietId);

    boolean existsByName(String name);

}
