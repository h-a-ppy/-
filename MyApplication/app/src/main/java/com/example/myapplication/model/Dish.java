package com.example.myapplication.model;

import java.io.Serializable;

public class Dish implements Serializable {
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private int quantity;

    public Dish(String name, String description, double price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = 0;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}