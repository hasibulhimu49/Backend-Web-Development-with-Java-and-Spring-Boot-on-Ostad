package com.productInventoryAPI.controller;

import com.productInventoryAPI.entity.Product;
import com.productInventoryAPI.response.ApiResponse;
import com.productInventoryAPI.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping
    ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody Product product) {
        log.debug("Received request to create product : {}", product);
        ApiResponse<Product> responseProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseProduct);
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        log.debug("Received request to get all products");
        ApiResponse<List<Product>> productList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        log.debug("Received request to get product by id : {}", id);
        ApiResponse<Product> product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        log.debug("Received request to update product with id : {}", id);
        ApiResponse<Product> updateProduct = productService.updateProduct(id,product);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("Received request to delete product with id : {}", id);
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
