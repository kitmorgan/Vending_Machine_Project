package com.techelevator;

import java.math.BigDecimal;
import java.util.*;

public class VendingMachineCLI {

    public VendingMachineCLI() throws Exception {
        InventoryManager inventoryManager = new InventoryManager();

        // Main Menu
        while (true) {
            System.out.print("(1) Display Vending Machine Items\n(2) Purchase\n(3) Exit");
            String userInput = getUserInput123();

            while (true) {
                //Display
                if (userInput.equalsIgnoreCase("1")) {
                    displayInventory(inventoryManager);
                    System.out.println("");
                    break;
                }

                // Purchase
                if (userInput.equalsIgnoreCase("2")) {
                    while (true) {
                        System.out.println("Current money provided: " + inventoryManager.moneyManager.getCurrentMoney());
                        System.out.println("");
                        System.out.println("(1) Feed Money\n(2) Select Product \n(3) Finish Transaction \n");
                        userInput = getUserInput123();
                        // Feed Money
                        if (userInput.matches("1")) {
                            System.out.print("Enter a whole dollar amount to feed money: ");
                            userInput = getUserInputWholeNumber();
                            BigDecimal deposit = new BigDecimal(userInput);
                            inventoryManager.moneyManager.feedMoney(deposit);
                        }
                        
                    }
                }
                if (userInput.equalsIgnoreCase("3")){
                    return;
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    public void run() {
    }

    public void displayInventory(InventoryManager input) {
        List<String> displayList = new ArrayList<>();
        for (Map.Entry<String, Item> entry : input.itemMap.entrySet()) {
            String itemInfo = null;
            Item item = entry.getValue();
            itemInfo = item.getSlot() + " | " + item.getName() + " ";
            if (item.getQuantity() <= 0) {
                itemInfo += "SOLD OUT";
            } else {
                itemInfo += "$" + item.getPrice() + " | Quantity: " + item.getQuantity();
            }
            displayList.add(itemInfo);
        }
        Collections.sort(displayList);
        for (String itemInfo : displayList) {
            System.out.println(itemInfo);
        }
    }

    public void selection123() {
        System.out.print("\nSelect 1, 2, or 3: ");
    }

    public String getUserInput123() {
        String answer = "";
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            selection123();
            userInput = scanner.nextLine();
            if (userInput.matches("[123]")) {
                answer = userInput;
                return answer;
            }
        }

    }
    public String getUserInputWholeNumber() {
        String answer = "";
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            userInput = scanner.nextLine();
            try{
            int deposit = Integer.parseInt(userInput);
            userInput = String.valueOf(deposit) + ".00";
            return userInput;
            }catch (NumberFormatException e){
                System.out.print("Enter a valid whole number: ");
            }
        }

    }
}
