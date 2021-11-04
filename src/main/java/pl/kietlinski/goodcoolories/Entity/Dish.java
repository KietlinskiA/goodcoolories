package pl.kietlinski.goodcoolories.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private long dishId;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 1000, nullable = false)
    private String description;
    @Column(length = 100)
    private String photo;

    @OneToOne
    private Recipe recipe;

    public Dish(String name, String description, String photo) {
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", recipe=" + recipe +
                '}';
    }
}
