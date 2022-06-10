package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class InventoryManagerTests {
    @Test
    public void input_file_has_same_number_of_classes() throws Exception{
        //arrange
        InventoryManager inventoryManager = new InventoryManager();
        int expected = 16;
        //act
        int actual = inventoryManager.itemMap.size();

        //assert
        Assert.assertEquals("should be the same number", expected, actual);
    }

    @Test
    public void values_from_input_file_contains_same_values_in_map() throws Exception{
        //arrange
        InventoryManager inventoryManager = new InventoryManager();
        Candy candy = new Candy("B1", "Moonpie", new BigDecimal("1.80"));
        //B1|Moonpie|1.80|Candy
        Item expected = candy;
        //act
        Item actual = inventoryManager.itemMap.get("B1");

        //assert
        Assert.assertEquals(expected, actual);
    }
}
