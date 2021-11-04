package pl.kietlinski.goodcoolories.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "level_of_difficulty")
    private boolean levelOfDifficulty;
    @Column(name = "preparation_time")
    private int preparationTime;
    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "recipe")
    private Set<Ingredient> ingredientSet;

    public Recipe(boolean levelOfDifficulty, int preparationTime, String description) {
        this.levelOfDifficulty = levelOfDifficulty;
        this.preparationTime = preparationTime;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", levelOfDifficulty=" + levelOfDifficulty +
                ", preparationTime=" + preparationTime +
                ", description='" + description + '\'' +
                '}';
    }
}

