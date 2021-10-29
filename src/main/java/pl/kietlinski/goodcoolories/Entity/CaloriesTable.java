package pl.kietlinski.goodcoolories.Entity;

import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calories_tables")
public class CaloriesTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calories_table_id")
    private long caloriesTableId;
    @Column(length = 2000, nullable = false)
    @Unsigned
    private int kcal;
    @Column(length = 1000, nullable = false)
    @Unsigned
    private double protein;
    @Column(length = 1000, nullable = false)
    @Unsigned
    private double fat;
    @Column(length = 1000, nullable = false)
    @Unsigned
    private double carbohydrates;
}
