package pl.kietlinski.goodcoolories.Entity;

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
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double proportions;

    @ManyToOne
    private IngredientInfo ingredientInfo;
    @ManyToOne
    private Recipe recipe;

    public Ingredient(String name, double proportions) {
        this.name = name;
        this.proportions = proportions;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", name='" + name + '\'' +
                ", proportions=" + proportions +
                ", ingredientInfo=" + ingredientInfo +
                ", recipe=" + recipe +
                '}';
    }
}
