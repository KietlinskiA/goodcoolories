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
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long recipeId;
    @Column(name = "level_of_difficulty", length = 6)
    private String levelOfDifficulty;
    @Column(name = "preparation_time", nullable = false, columnDefinition = "Integer UNSIGNED", length = 3)
    private int preparationTime;
    @Column(length = 1000, scale = 100)
    private String description;

    @OneToOne
    private Dish dish;

    @OneToMany(mappedBy = "recipe")
    private Set<IngredientRecipe> ingredientRecipeSet;

    public Recipe(String levelOfDifficulty, int preparationTime, String description) {
        this.levelOfDifficulty = levelOfDifficulty;
        this.preparationTime = preparationTime;
        this.description = description;
    }

    @Override
    public String toString() {
        List<Long> ingredientIdList = new ArrayList<>();
        ingredientRecipeSet.forEach(ingredientRecipe -> ingredientIdList.add(ingredientRecipe.getIngredientRecipeId()));
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", levelOfDifficulty='" + levelOfDifficulty + '\'' +
                ", preparationTime=" + preparationTime +
                ", description='" + description + '\'' +
                ", dish=" + dish +
                ", ingredientIds=" + ingredientIdList +
                '}';
    }
}

