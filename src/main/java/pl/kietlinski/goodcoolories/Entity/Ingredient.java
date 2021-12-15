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
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private long ingredientId;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double kcal;
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double protein;
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double fat;
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double carbohydrates;

    @OneToMany(mappedBy = "ingredient")
    private Set<IngredientRecipe> ingredientRecipeSet;

    public Ingredient(String name, int kcal, double protein, double fat, double carbohydrates) {
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }


    @Override
    public String toString() {
        List<Long> ingredientIdList = new ArrayList<>();
        ingredientRecipeSet.forEach(ingredientRecipe -> ingredientIdList.add(ingredientRecipe.getIngredientRecipeId()));
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", name='" + name + '\'' +
                ", kcal=" + kcal +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrates=" + carbohydrates +
                ", ingredientRecipeIds=" + ingredientIdList +
                '}';
    }
}
