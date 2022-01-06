package pl.kietlinski.goodcoolories.Model;

import pl.kietlinski.goodcoolories.Entity.Order;

public class OrderBuilder {
    private String activity;
    private String dietWish;
    private int dishCount;
    private String comment;
    private String status;

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
        return new Order(activity, dietWish, dishCount, comment, status);
    }
}