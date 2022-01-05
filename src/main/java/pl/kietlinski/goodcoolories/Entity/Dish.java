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
@Table(name = "dishes")
public class Dish implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private long dishId;
    @Column(length = 100, nullable = false, unique = true)
    private String name;
    @Column(length = 500)
    private String photo;

    @ManyToMany(mappedBy = "dishSet")
    private Set<Diet> dietSet;

    @OneToOne(mappedBy = "dish")
    private Recipe recipe;

    public Dish(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    @Override
    public String toString() {
        List<Long> dishIds = new ArrayList<>();
        dietSet.forEach(diet -> dishIds.add(diet.getDietId()));
        return "Dish{" +
                "dishId=" + dishId +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", recipeId=" + recipe.getRecipeId() +
                ", diets=" + dishIds +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
