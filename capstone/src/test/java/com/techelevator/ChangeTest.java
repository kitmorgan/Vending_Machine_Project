package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class ChangeTest {

    @Test
    public void one_returns_four_quarters() throws Exception{
        MoneyManager moneyManager = new MoneyManager();
        moneyManager.feedMoney(BigDecimal.ONE);
        int expectedQ = 4;

        Map<String, Integer> outputMap = moneyManager.coinsDue();
        int outcomeQ = outputMap.get("quarter");
        System.out.println(outputMap);
        Assert.assertEquals(expectedQ, outcomeQ);



    }
    @Test
    public void seventycents_returns_2_quarters_and_2_dimes()  throws Exception{
        MoneyManager moneyManager = new MoneyManager();
        moneyManager.feedMoney(new BigDecimal(".70"));
        int expectedQ = 2;
        int expectedD = 2;
        int expectedN = 0;
        int expectedP = 0;

        Map<String, Integer> outputMap = moneyManager.coinsDue();
        int outcomeQ = outputMap.get("quarter");
        int outcomeD = outputMap.get("dime");
        Assert.assertEquals(expectedQ, outcomeQ);
        Assert.assertEquals(expectedD, outcomeD);

    }
    @Test
    public void sixtysix_returns_2_quarters_and_1_dimes_1nickel_1penny()  throws Exception{
        MoneyManager moneyManager = new MoneyManager();
        moneyManager.feedMoney(new BigDecimal(".66"));
        int expectedQ = 2;
        int expectedD = 1;
        int expectedN = 1;
        int expectedP = 1;

        Map<String, Integer> outputMap = moneyManager.coinsDue();
        int outcomeQ = outputMap.get("quarter");
        int outcomeD = outputMap.get("dime");
        int outcomeN = outputMap.get("nickel");
        int outcomeP = outputMap.get("penny");
        Assert.assertEquals(expectedQ, outcomeQ);
        Assert.assertEquals(expectedD, outcomeD);
        Assert.assertEquals(expectedN, outcomeN);
        Assert.assertEquals(expectedP, outcomeP);
    }
}
