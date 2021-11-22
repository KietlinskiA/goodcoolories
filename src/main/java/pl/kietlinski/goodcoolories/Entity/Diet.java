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
@Table(name = "diets")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    private long dietId;
    @Column(length = 1000)
    private String token;

    @OneToOne(mappedBy = "diet")
    private Order order;

    @OneToMany(mappedBy = "diet")
    private List<Dish> dishList;

    public Diet(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        List<Long> idList = new ArrayList<>();
        for(Dish d : dishList) {
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
