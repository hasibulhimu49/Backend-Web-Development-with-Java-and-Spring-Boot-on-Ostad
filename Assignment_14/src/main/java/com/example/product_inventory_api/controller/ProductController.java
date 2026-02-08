package com.example.product_inventory_api.controller;

import com.example.product_inventory_api.model.entity.Product;
import com.example.product_inventory_api.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/in")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService service;

    @PostMapping
    //@RequestBody
    public Product createProduct(@Valid @RequestBody Product product)
    {
        log.debug("Receved request to create product: {}",product);
        return service.createProduct(product);
    }



    @GetMapping
    public List<Product> getAllProduct()
    {
       return service.getALlProduct();
    }



    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id)
    {
        log.debug("Receved request to get product: {}",id);
        return service.getProductById(id);
    }



    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product)
    {
        log.debug("Receved request to update product: {}",product);
        return service.updateProduct(id,product);
    }



    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        log.debug("Receved request to delete product: {}",id);
        service.deleteProduct(id);
    }



}