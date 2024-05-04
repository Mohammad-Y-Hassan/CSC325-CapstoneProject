package com.example.capstoneproject;

public class Item {
    private String id;
    private String name;
    private double price;

    //Default Constructor
    public Item(){
    }
    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    //getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    };

    public void setName(String name) {};

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
