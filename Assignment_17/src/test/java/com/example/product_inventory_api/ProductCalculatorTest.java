package com.example.product_inventory_api;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductCalculatorTest {


    @Test
    void testToCalculateProductPriceNoDiscount()
    {
        Double result=ProductCalculator.calculateDiscountedPrice(200,0);
        assertEquals(200,result);
    }

    @Test
    void testToCalculateProductPriceHalfDiscount()
    {
        Double result=ProductCalculator.calculateDiscountedPrice(200,50);
        assertEquals(100,result);
    }

    @Test
    void testToCalculateProductPriceFullDiscount()
    {
        Double result=ProductCalculator.calculateDiscountedPrice(200,100);
        assertEquals(0,result);
    }

    @Test
    void testToCalculateProductPrice_InvalidDiscount()
    {
        assertThrows(IllegalArgumentException.class,()->ProductCalculator.calculateDiscountedPrice(100,110));
    }




    @Test
    void testIsQuantitySufficient_EnoughQuantity()
    {
        Boolean result=ProductCalculator.isQuantitySufficient(20,10);
        assertEquals(true,result);
    }

    @Test
    void testIsQuantitySufficient_NotEnoughQuantity()
    {
        assertThrows(IllegalArgumentException.class,()->ProductCalculator.isQuantitySufficient(20,30));
    }


}
