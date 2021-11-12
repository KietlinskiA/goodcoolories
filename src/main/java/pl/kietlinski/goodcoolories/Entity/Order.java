package pl.kietlinski.goodcoolories.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    @Email
    private String eaddress;
    @Column(length = 9, nullable = false)
    private String phone;
    @Column(length = 100, nullable = false)
    private String street;
    @Column(length = 6, nullable = false)
    private String zip;
    @Column(length = 100, nullable = false)
    private String city;
    @Column(length = 3, nullable = false)
    private int age;
    @Column(length = 3, nullable = false)
    private int height;
    @Column(length = 6, nullable = false)
    private String sex;
    @Column(length = 6, nullable = false)
    private String activity;
    @Column(length = 1, nullable = false)
    @Min(3)
    @Max(6)
    private int dishCount;
    @Column(length = 1000, nullable = false)
    private String comment;

    public Order(long orderId, String name, String eaddress, String phone, String street, String zip, String city, int age, int height, String sex, String activity, int dishCount, String comment) {
        this.orderId = orderId;
        this.name = name;
        this.eaddress = eaddress;
        this.phone = phone;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.age = age;
        this.height = height;
        this.sex = sex;
        this.activity = activity;
        this.dishCount = dishCount;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", name='" + name + '\'' +
                ", eaddress='" + eaddress + '\'' +
                ", phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", sex='" + sex + '\'' +
                ", activity='" + activity + '\'' +
                ", dishCount=" + dishCount +
                ", comment='" + comment + '\'' +
                '}';
    }
}

