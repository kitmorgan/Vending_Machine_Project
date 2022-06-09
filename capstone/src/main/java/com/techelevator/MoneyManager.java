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
        final BigDecimal QUARTER = new BigDecimal(".25");
        final BigDecimal DIME = new BigDecimal(".10");
        final BigDecimal NICKEL = new BigDecimal(".05");
        final BigDecimal PENNY = new BigDecimal(".01");
        Map<String, Integer> output = new HashMap<>();
        output.put("QUARTER", 0);
        output.put("DIME", 0);
        output.put("NICKEL", 0);
        output.put("PENNY", 0);
        if (currentMoney.divide(QUARTER).compareTo(BigDecimal.ONE) >= 0) { // is there at least one quarter owed? .20/.25 >= 1 (false)
            int quarterDue = getChangeDue().divide(QUARTER, RoundingMode.HALF_DOWN).intValue(); // how many quarters are they owed? 2.00 / .25 = 8
            output.put("QUARTER", quarterDue); // put it in the map ("QUARTER", 8)
            BigDecimal removeMe = BigDecimal.valueOf(quarterDue).multiply(QUARTER); // what is the total value of the quarters we are giving them? .25 * 8 = 2.00
            this.currentMoney = currentMoney.subtract(removeMe); // how much money do we owe after we give them quarters? 2.00 - 2.00 = 0.00
        }
        if(currentMoney.divide(DIME).compareTo((BigDecimal.ONE)) >= 0){
            int dimesDue = getChangeDue().divide(DIME, RoundingMode.HALF_DOWN).intValue();
            output.put("DIME", dimesDue);
            BigDecimal removeMe = BigDecimal.valueOf(dimesDue).multiply(DIME);
            this.currentMoney = currentMoney.subtract(removeMe);
        }
        if (currentMoney.divide(NICKEL).compareTo((BigDecimal.ONE)) >= 0) {
            int nicklesDue = getChangeDue().divide(NICKEL, RoundingMode.HALF_DOWN).intValue();
            output.put("NICKEL", nicklesDue);
            BigDecimal removeMe = BigDecimal.valueOf(nicklesDue).multiply(NICKEL);
            this.currentMoney = currentMoney.subtract(removeMe);
        }
        if(currentMoney.compareTo(BigDecimal.ZERO) > 0){
            int penniesDue = getChangeDue().divide(PENNY,RoundingMode.HALF_DOWN).intValue();
            output.put("PENNY", penniesDue);
            BigDecimal removeMe = BigDecimal.valueOf(penniesDue).multiply(PENNY);
            this.currentMoney = currentMoney.subtract(removeMe);
        }
        return output;
    }


}
