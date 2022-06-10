package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class MoneyManager {
    private BigDecimal currentMoney = new BigDecimal(0);
    private BigDecimal changeDue;


    // getters
    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public BigDecimal getChangeDue() {
        return currentMoney;
    }

    public void feedMoney(BigDecimal deposit){
        this.currentMoney = this.currentMoney.add(deposit);
    }

    public void payFor(BigDecimal price){
        this.currentMoney = this.currentMoney.subtract(price);
    }

    public Map<String, Integer> coinsDue() {
        final BigDecimal quarter = new BigDecimal(".25");
        final BigDecimal dime = new BigDecimal(".10");
        final BigDecimal nickel = new BigDecimal(".05");
        final BigDecimal penny = new BigDecimal(".01");
        Map<String, Integer> output = new HashMap<>();
        if (currentMoney.divide(quarter).compareTo(BigDecimal.ONE) >= 0) { // is there at least one quarter owed? .20/.25 >= 1 (false)
            int quarterDue = getChangeDue().divide(quarter, RoundingMode.HALF_DOWN).intValue(); // how many quarters are they owed? 2.00 / .25 = 8
            output.put("quarter", quarterDue); // put it in the map ("quarter", 8)
            BigDecimal removeMe = BigDecimal.valueOf(quarterDue).multiply(quarter); // what is the total value of the quarters we are giving them? .25 * 8 = 2.00
            this.currentMoney = currentMoney.subtract(removeMe); // how much money do we owe after we give them quarters? 2.00 - 2.00 = 0.00
        }
        if(currentMoney.divide(dime).compareTo((BigDecimal.ONE)) >= 0){
            int dimesDue = getChangeDue().divide(dime, RoundingMode.HALF_DOWN).intValue();
            output.put("dime", dimesDue);
            BigDecimal removeMe = BigDecimal.valueOf(dimesDue).multiply(dime);
            this.currentMoney = currentMoney.subtract(removeMe);
        }
        if (currentMoney.divide(nickel).compareTo((BigDecimal.ONE)) >= 0) {
            int nicklesDue = getChangeDue().divide(nickel, RoundingMode.HALF_DOWN).intValue();
            output.put("nickel", nicklesDue);
            BigDecimal removeMe = BigDecimal.valueOf(nicklesDue).multiply(nickel);
            this.currentMoney = currentMoney.subtract(removeMe);
        }
        if(currentMoney.compareTo(BigDecimal.ZERO) > 0){
            int penniesDue = getChangeDue().divide(penny,RoundingMode.HALF_DOWN).intValue();
            output.put("penny", penniesDue);
            BigDecimal removeMe = BigDecimal.valueOf(penniesDue).multiply(penny);
            this.currentMoney = currentMoney.subtract(removeMe);
        }
        return output;

    }


}
