package com.techelevator;

import java.math.BigDecimal;

abstract class Item {
    // instance variables
    private String slot;
    private String name;
    private BigDecimal price;
    private int quantity = 5;
    protected String noise;

    // constructor
    public Item(String slot, String name, BigDecimal price){
        this.slot = slot;
        this.name = name;
        this.price = price;
    }

    // getters and setters
    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }
}
