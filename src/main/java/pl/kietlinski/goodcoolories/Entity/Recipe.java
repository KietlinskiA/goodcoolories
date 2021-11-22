package pl.kietlinski.goodcoolories.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "level_of_difficulty")
    private String levelOfDifficulty;
    @Column(name = "preparation_time")
    private int preparationTime;
    @Column(length = 1000)
    private String description;

    @OneToOne(mappedBy = "recipe")
    private Dish dish;

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredientList;

    public Recipe(String levelOfDifficulty, int preparationTime, String description) {
        this.levelOfDifficulty = levelOfDifficulty;
        this.preparationTime = preparationTime;
        this.description = description;
    }

    @Override
    public String toString() {
        List<Long> idList = new ArrayList<>();
        for(Ingredient i : ingredientList) {
            idList.add(i.getIngredientId());
        }
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", levelOfDifficulty='" + levelOfDifficulty + '\'' +
                ", preparationTime=" + preparationTime +
                ", description='" + description + '\'' +
                ", dish=" + dish +
                ", ingredientIds=" + idList +
                '}';
    }
}

