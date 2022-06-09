package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Item{

    public Drink(String slot, String name, BigDecimal price) {
        super(slot, name, price);
        this.noise = "Glug Glug, Yum!";
    }
}
