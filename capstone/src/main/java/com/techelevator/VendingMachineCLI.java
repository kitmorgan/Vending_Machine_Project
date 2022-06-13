package com.techelevator;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineCLI {

    public VendingMachineCLI() throws Exception {
        InventoryManager inventoryManager = new InventoryManager();

        // Main Menu
        while (true) {
            String userInput = "";
            System.out.print("\n(1) Display Vending Machine Items\n(2) Purchase\n(3) Exit\n");
            userInput = getUserInput123();
            //Display
            if (userInput.equalsIgnoreCase("1")) {
                displayInventory(inventoryManager);
            }

            // Purchase
            else if (userInput.equalsIgnoreCase("2")) {
                while (true) {
                    System.out.println("\nCurrent money provided: " + inventoryManager.moneyManager.getCurrentMoney());
                    System.out.println("");
                    System.out.println("(1) Feed Money\n(2) Select Product \n(3) Finish Transaction");
                    userInput = getUserInput123();
                    // Feed Money
                    if (userInput.equalsIgnoreCase("1")) {
                        System.out.print("Enter a whole dollar amount to feed money: ");
                        userInput = getUserInputWholeNumber();
                        BigDecimal deposit = new BigDecimal(userInput);
                        inventoryManager.moneyManager.feedMoney(deposit);
                    }
                    // Select Product
                    if (userInput.equalsIgnoreCase("2")) {
                        displayInventory(inventoryManager);
                        getUserInputProductSelection(inventoryManager);
                    }
                    // Finish Transaction
                    if (userInput.equalsIgnoreCase("3")) {
                        finishTransaction(inventoryManager);
                        break;
                    }

                }
            }
            // Exit
            else if (userInput.equalsIgnoreCase("3")) {
                System.out.println("");
                System.out.println("Thank you for using our Vending Machine!");
                return;
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
        System.out.print("\nInput 1, 2, or 3: ");
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
            try {
                int deposit = Integer.parseInt(userInput);
                userInput = String.valueOf(deposit) + ".00";
                return userInput;
            } catch (NumberFormatException e) {
                System.out.print("Input a valid whole number: ");
            }
        }

    }

    public void getUserInputProductSelection(InventoryManager inventoryManager) {
        String userInput;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter a slot number to purchase an item: ");
            userInput = scanner.nextLine();
            try {
                inventoryManager.dispense(userInput);
                Item item = inventoryManager.itemMap.get(userInput);
                String itemName = item.getName();
                BigDecimal price = item.getPrice();
                String noise = item.getNoise();
                BigDecimal money = inventoryManager.moneyManager.getCurrentMoney();
                System.out.println(itemName + " | S" + price + " |  S" + money + "\n" + noise);
                return;

            } catch (NullPointerException npe) {
                System.out.println("Selection Not Available");
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }

    }

    public void finishTransaction(InventoryManager inventoryManager) throws Exception {
        Map<String, Integer> coinsReturned = new HashMap<>();
        coinsReturned = inventoryManager.moneyManager.coinsDue();
        for (Map.Entry<String, Integer> entry : coinsReturned.entrySet()) {
            //
            String line = String.valueOf(entry.getValue()) + " " + entry.getKey() + "s returned";
            System.out.println(line);
        }
    }
}
