package pl.kietlinski.goodcoolories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kietlinski.goodcoolories.Entity.CaloriesTable;

@Repository
public interface CaloriesTableRepository extends JpaRepository<CaloriesTable, Long> {
}
