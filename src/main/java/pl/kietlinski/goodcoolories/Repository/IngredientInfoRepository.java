package pl.kietlinski.goodcoolories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kietlinski.goodcoolories.Entity.IngredientInfo;

@Repository
public interface IngredientInfoRepository extends JpaRepository<IngredientInfo, Long> {
}
