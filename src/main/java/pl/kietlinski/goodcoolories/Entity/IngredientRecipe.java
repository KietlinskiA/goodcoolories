package pl.kietlinski.goodcoolories.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredients_recipes")
public class IngredientRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_recipe_id")
    private long ingredientRecipeId;
    @Column(columnDefinition = "Float UNSIGNED", nullable = false)
    private double proportions;

    @ManyToOne
    private Ingredient ingredient;
    @ManyToOne
    private Recipe recipe;

    public IngredientRecipe(double proportions) {
        this.proportions = proportions;
    }

    @Override
    public String toString() {
        return "IngredientRecipe{" +
                "ingredientRecipeId=" + ingredientRecipeId +
                ", proportions=" + proportions +
                ", ingredientId=" + ingredient.getIngredientId() +
                ", recipe=" + recipe +
                '}';
    }
}
