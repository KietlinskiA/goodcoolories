package pl.kietlinski.goodcoolories.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredients_info")
public class IngredientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_info_id")
    private long ingredientInfoId;
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double kcal;
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double protein;
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double fat;
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double carbohydrates;

    @OneToMany(mappedBy = "ingredientInfo")
    private Set<Ingredient> ingredientSet;

    public IngredientInfo(int kcal, double protein, double fat, double carbohydrates) {
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }


    @Override
    public String toString() {
        List<Long> ingredientIdList = new ArrayList<>();
        ingredientSet.forEach(ingredient -> ingredientIdList.add(ingredient.getIngredientId()));
        return "IngredientInfo{" +
                "ingredientInfoId=" + ingredientInfoId +
                ", kcal=" + kcal +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrates=" + carbohydrates +
                ", ingredientIds=" + ingredientIdList +
                '}';
    }
}
