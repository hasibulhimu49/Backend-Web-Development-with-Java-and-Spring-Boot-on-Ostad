package com.productInventoryAPI.services;

import com.productInventoryAPI.entity.Product;
import com.productInventoryAPI.response.ApiResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ApiResponse<Product> createProduct(@Valid Product product);

    ApiResponse<List<Product>> getAllProducts();

    ApiResponse<Product> getProductById(Long id);

    ApiResponse<Product> updateProduct(Long id, @Valid Product product);

    void  deleteProductById(Long id);
}
