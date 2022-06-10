package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Log {
    static String timeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
    static File file = new File("log.csv");
    public static void clearLog() throws Exception{
        //clears log when called
        try(PrintWriter writer = new PrintWriter(file)){
            writer.print("");
        }
    }
    // date, time, itemName, Slot, Price, CM
    public static void sale(Item item, BigDecimal currentMoney) throws Exception {

        String itemName = item.getName();
        String slot = item.getSlot();
        BigDecimal price = item.getPrice();
        BigDecimal money = currentMoney;

        try(FileOutputStream fos = new FileOutputStream(file, true);

            PrintWriter writer = new PrintWriter(fos)){
            writer.println(timeDate + " " + itemName + " " + slot + " $" + price + " $" + money);
        }

    }
    public static void fed(BigDecimal deposit, BigDecimal currentMoney) throws Exception{
        try(FileOutputStream fos = new FileOutputStream(file, true);
            PrintWriter writer = new PrintWriter(fos)) {
            writer.println(timeDate + " FEED MONEY: $" + deposit + " $" + currentMoney);
        }
    }

    public static void change(BigDecimal currentMoney) throws Exception{
        try(FileOutputStream fos = new FileOutputStream(file, true);
            PrintWriter writer = new PrintWriter(fos)) {
            writer.println(timeDate + " GIVE CHANGE $" + currentMoney + " $0.00");
        }
    }

}
