package pl.kietlinski.goodcoolories.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kietlinski.goodcoolories.Entity.CaloriesTable;
import pl.kietlinski.goodcoolories.Repository.CaloriesTableRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CaloriesTableService {

    private List<CaloriesTable> caloriesTableList;
    private CaloriesTableRepository caloriesTableRepository;

    @Autowired
    public CaloriesTableService(CaloriesTableRepository caloriesTableRepository) {
        this.caloriesTableRepository = caloriesTableRepository;
        this.caloriesTableList = new ArrayList<>();
        initDatabase();
    }

    private void initDatabase() {
        CaloriesTable caloriesTable1 = new CaloriesTable(1L, 100, 23, 20, 24);
        CaloriesTable caloriesTable2 = new CaloriesTable(2L, 200, 32, 30, 34);
        CaloriesTable caloriesTable3 = new CaloriesTable(3L, 300, 10, 10, 14);
        caloriesTableRepository.save(caloriesTable1);
        caloriesTableRepository.save(caloriesTable2);
        caloriesTableRepository.save(caloriesTable3);
    }

    public List<CaloriesTable> getCaloriesTableList() {
        return caloriesTableRepository.findAll();
    }

}
