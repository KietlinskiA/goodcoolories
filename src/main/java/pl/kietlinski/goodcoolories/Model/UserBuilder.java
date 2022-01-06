package pl.kietlinski.goodcoolories.Model;

import pl.kietlinski.goodcoolories.Entity.User;

public class UserBuilder {
    private String name;
    private String eaddress;
    private String phone;
    private String street;
    private String zip;
    private String city;
    private int age;
    private int height;
    private String sex;

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEaddress(String eaddress) {
        this.eaddress = eaddress;
        return this;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public UserBuilder setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public UserBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public UserBuilder setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public User createUser() {
        return new User(name, eaddress, phone, street, zip, city, age, height, sex);
    }
}