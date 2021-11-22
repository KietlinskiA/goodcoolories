package pl.kietlinski.goodcoolories.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kietlinski.goodcoolories.Entity.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {

    boolean existsByToken(String token);

    Diet findByToken(String token);


}
