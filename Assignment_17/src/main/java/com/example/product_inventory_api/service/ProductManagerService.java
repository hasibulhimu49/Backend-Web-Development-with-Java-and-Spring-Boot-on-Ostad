package com.example.product_inventory_api.service;

import com.example.product_inventory_api.exception.ProductNotFoundException;
import com.example.product_inventory_api.model.entity.Product;
import com.example.product_inventory_api.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductManagerService {

    private final ProductRepository productRepository;

    public Product  findProductBySku(String sku)
    {
     Product product=productRepository.findBySku(sku).orElseThrow(()->new ProductNotFoundException("The sku is not exist"));
     return product;

    }


    public Product restockProduct(String sku,Integer addQuantity)
    {
        Product product=productRepository.findBySku(sku).orElseThrow(()->new ProductNotFoundException("The sku is not exist for add"));
        product.setQuantity(product.getQuantity()+addQuantity);
        return productRepository.save(product);



    }
}
