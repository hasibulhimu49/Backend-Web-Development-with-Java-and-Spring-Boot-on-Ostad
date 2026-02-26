package com.example.product_inventory_api;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public  class ProductCalculator {

   public static Double calculateDiscountedPrice(double originalPrice, double discountRate)
    {
        if(discountRate<0 || discountRate>100)
        {
            throw new IllegalArgumentException("discount must be between 0 and 100");
        }

        Double finalPrice=originalPrice-  (double) ((originalPrice*discountRate)/100);
        return finalPrice;

    }

    public static Boolean isQuantitySufficient(int currentQuantity, int requiredQuantity)
    {

        if(requiredQuantity>currentQuantity)
        {
            throw new IllegalArgumentException("Quantity must be greater than required quantity");
        }

        return true;
    }
}
