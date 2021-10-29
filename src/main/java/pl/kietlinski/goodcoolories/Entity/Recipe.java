package pl.kietlinski.goodcoolories.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}

