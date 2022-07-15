package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InventoryManager {
    protected Map<String, Item> itemMap = new HashMap<>();//Slot, Slot, Item Name, Price
    public MoneyManager moneyManager = new MoneyManager();
    private File inputFile = new File("vendingmachine.csv");

    public InventoryManager() throws Exception {
        Log.clearLog();
        try (Scanner fileScanner = new Scanner(inputFile);) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] lineSplitter = line.split("\\|");
                String slot = lineSplitter[0];
                String itemName = lineSplitter[1];
                String stringPrice = lineSplitter[2];
                BigDecimal priceBD = new BigDecimal(stringPrice);
                String itemType = lineSplitter[3];
                Item item = null;
                String noise = "";

                if (itemType.equalsIgnoreCase("Chip")) {
                    item = new Chip(slot, itemName, priceBD);
                } else if (itemType.equalsIgnoreCase("Candy")) {
                    item = new Candy(slot, itemName, priceBD);
                } else if (itemType.equalsIgnoreCase("Drink")) {
                    item = new Drink(slot, itemName, priceBD);
                } else if (itemType.equalsIgnoreCase("Gum")) {
                    item = new Gum(slot, itemName, priceBD);
                } else {
                    throw new Exception("could not create new item: " + itemType);
                }
                itemMap.put(slot, item);

            }
        } catch (FileNotFoundException fnf) {
            System.out.println("file not found");
        }

    }

    public void dispense(String slot) throws Exception {
        if (!itemMap.containsKey(slot)) {
            throw new Exception("Item not available");
        }
        BigDecimal price = itemMap.get(slot).getPrice();
        int enoughFunds = moneyManager.getCurrentMoney().compareTo(price);
        // check to see if user has enough money
        if (enoughFunds == -1) {
            throw new Exception("Deposit more money to purchase an item");
        }
        // check to see if quantity is enough
        if (itemMap.get(slot).getQuantity() - 1 < 0) {
            throw new Exception("Item is sold out");
        }
        // give item to customer, pay, log
        itemMap.get(slot).give();
        moneyManager.payFor(price);
        Log.sale(itemMap.get(slot), moneyManager.getCurrentMoney());
    }


}
