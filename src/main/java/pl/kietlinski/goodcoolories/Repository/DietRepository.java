package pl.kietlinski.goodcoolories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kietlinski.goodcoolories.Entity.Diet;

import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {

    boolean existsByToken(String token);

    Diet findByToken(String token);

    @Query(value = "SELECT token FROM diets ORDER BY token DESC LIMIT 1", nativeQuery = true)
    String findHighestTokenNumberInDb();

}
