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
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private long ingredientId;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 500, nullable = false)
    @Unsigned
    private int kcal;
    @Column(scale = 100, nullable = false)
    @Unsigned
    private double protein;
    @Column(scale = 100, nullable = false)
    @Unsigned
    private double fat;
    @Column(scale = 100, nullable = false)
    @Unsigned
    private double carbohydrates;

}
