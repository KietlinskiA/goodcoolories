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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String eaddress;
    @Column(length = 9, nullable = false, unique = true)
    private String phone;
    @Column(length = 100, nullable = false)
    private String street;
    @Column(length = 6, nullable = false)
    private String zip;
    @Column(length = 100, nullable = false)
    private String city;
    @Column(length = 2, nullable = false)
    private int age;
    @Column(length = 3, nullable = false)
    private int height;
    @Column(length = 9, nullable = false)
    private String sex;

    @OneToMany(mappedBy = "user")
    Set<Order> orderSet;

    public User(String name, String eaddress, String phone, String street, String zip, String city, int age, int height, String sex) {
        this.name = name;
        this.eaddress = eaddress;
        this.phone = phone;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.age = age;
        this.height = height;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", eaddress='" + eaddress + '\'' +
                ", phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", sex='" + sex + '\'' +
                '}';
    }
}

