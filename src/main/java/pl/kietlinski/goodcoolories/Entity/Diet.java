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
@Table(name = "diets")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    private long dietId;
    @Column(length = 3, scale = 999)
    private String token;

    @OneToOne(mappedBy = "diet")
    private Order order;

    @ManyToMany
    private Set<Dish> dishSet;

    public Diet(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        List<Long> idList = new ArrayList<>();
        for (Dish d : dishSet) {
            idList.add(d.getDishId());
        }
        return "Diet{" +
                "dietId=" + dietId +
                "token='" + token + '\'' +
                ", orderId=" + order.getOrderId() +
                ", dishIds=" + idList +
                '}';
    }

}
