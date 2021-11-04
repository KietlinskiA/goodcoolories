package pl.kietlinski.goodcoolories.Entity;

import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne
    private Recipe recipe;

    public Ingredient(String name, int kcal, double protein, double fat, double carbohydrates) {
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", name='" + name + '\'' +
                ", kcal=" + kcal +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
