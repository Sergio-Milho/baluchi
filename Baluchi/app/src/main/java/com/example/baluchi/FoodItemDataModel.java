package com.example.foodorderapp;

public class FoodItem {
    private String name;
    private String description;
    private double price;
    private String image;

    public FoodItem() {
        // Default constructor required for calls to DataSnapshot.getValue(FoodItem.class)
    }

    public FoodItem(String name, String description, double price, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
