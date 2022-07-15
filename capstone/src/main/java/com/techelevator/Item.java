package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {
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

    public void give() {
        this.quantity = quantity-1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return item.slot.equals(this.slot) && item.name.equalsIgnoreCase(this.name) && (item.price.compareTo(this.price) == 0);
    }

    @Override
    public String toString() {
        return "Item{" +
                "slot='" + slot + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", noise='" + noise + '\'' +
                '}';
    }
}
