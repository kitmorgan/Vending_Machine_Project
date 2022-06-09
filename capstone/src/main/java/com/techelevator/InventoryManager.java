package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Item> itemMap = new HashMap<>();//Slot, Slot, Item Name, Price
   // public MoneyManger moneyManger;
    public InventoryManager() {

    }

    public void dispense(String slot) throws Exception{
       // if(MoneyManager.get >= )
        if (itemMap.get(slot).getQuantity()-1  < 0 ) {
            throw new Exception("Insufficient Quantity");
        } else {
            itemMap.get(slot).dispense();
            //Log.dispense(Item item) add new line item to log
            //calls moneymanager
        }
    }


}
