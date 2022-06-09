package com.techelevator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Item> itemMap = new HashMap<>();//Slot, Slot, Item Name, Price
    public MoneyManager moneyManager;

    public InventoryManager() {

    }

    public void dispense(String slot) throws Exception {
        BigDecimal price = itemMap.get(slot).getPrice();
        int enoughFunds = moneyManager.getCurrentMoney().compareTo(price);
        // check to see if user has enough money
        if (enoughFunds == -1) {
            throw new Exception("Insufficient Funds");
        }
        // check to see if quantity is enough
        if (itemMap.get(slot).getQuantity() - 1 < 0) {
            throw new Exception("Insufficient Quantity");
        }
        // give item to customer, pay, log
        itemMap.get(slot).give();
        moneyManager.payFor(price);
        //Log.dispense(Item item) add new line item to log
    }


}
