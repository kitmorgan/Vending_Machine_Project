package com.techelevator;

import java.math.BigDecimal;

public class MoneyManager {
    private BigDecimal currentMoney = new BigDecimal(0);
    private BigDecimal changeDue;
    public final BigDecimal QUARTER = new BigDecimal(".25");
    public final BigDecimal DIME = new BigDecimal(".10");
    public final BigDecimal NICKEL = new BigDecimal(".05");
    public final BigDecimal PENNY = new BigDecimal(".01");

    // getters
    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public BigDecimal getChangeDue() {
        return currentMoney;
    }

    public void feedMoney(BigDecimal deposit){
        this.currentMoney.add(deposit);
    }

    public void payFor(BigDecimal price){
        this.currentMoney = this.currentMoney.subtract(price);
    }


}
