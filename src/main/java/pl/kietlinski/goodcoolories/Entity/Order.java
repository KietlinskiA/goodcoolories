package pl.kietlinski.goodcoolories.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;
    @Column(length = 8, nullable = false)
    private String activity;
    @Column(length = 8, nullable = false)
    private String dietWish;
    @Column(length = 1, nullable = false)
    private int dishCount;
    @Column(length = 1000, nullable = false)
    private String comment;
    @Column(length = 10, nullable = false)
    private String status;

    @OneToOne
    private Diet diet;

    @ManyToOne
    private User user;

    public Order(String activity, String dietWish, int dishCount, String comment, String status) {
        this.activity = activity;
        this.dietWish = dietWish;
        this.dishCount = dishCount;
        this.comment = comment;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", activity='" + activity + '\'' +
                ", dietWish='" + dietWish + '\'' +
                ", dishCount=" + dishCount +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                ", diet=" + diet.getDietId() +
                '}';
    }
}

