package pl.kietlinski.goodcoolories.Model;

import pl.kietlinski.goodcoolories.Entity.Order;

public class OrderBuilder {
    private String name;
    private String eaddress;
    private String phone;
    private String street;
    private String zip;
    private String city;
    private int age;
    private int height;
    private String sex;
    private String activity;
    private String dietWish;
    private int dishCount;
    private String comment;
    private String status;

    public OrderBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public OrderBuilder setEaddress(String eaddress) {
        this.eaddress = eaddress;
        return this;
    }

    public OrderBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public OrderBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public OrderBuilder setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public OrderBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public OrderBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public OrderBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public OrderBuilder setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public OrderBuilder setActivity(String activity) {
        this.activity = activity;
        return this;
    }

    public OrderBuilder setDietWish(String dietWish) {
        this.dietWish = dietWish;
        return this;
    }

    public OrderBuilder setDishCount(int dishCount) {
        this.dishCount = dishCount;
        return this;
    }

    public OrderBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public Order createOrder() {
        return new Order(name, eaddress, phone, street, zip, city, age, height, sex, activity, dietWish, dishCount, comment, status);
    }
}