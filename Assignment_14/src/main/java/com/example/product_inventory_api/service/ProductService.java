package com.example.product_inventory_api.service;

import com.example.product_inventory_api.exception.InvalidSkuFormatException;
import com.example.product_inventory_api.exception.ProductNotFoundException;
import com.example.product_inventory_api.exception.SkuAlreadyExistException;
import com.example.product_inventory_api.model.entity.Product;
import com.example.product_inventory_api.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    //Create Product
    public Product createProduct(Product product) {

        if(!product.getSku().matches("^SKU-[A-Za-z0-9]{8}$"))
        {
            throw new InvalidSkuFormatException("Invalid SKU Format");
        }

        if (repository.existsBySku(product.getSku())) {
            throw new SkuAlreadyExistException("SKU already existssssss");
        }

        Product saved =repository.save(product);
        log.info("Product created with ID: {} and SKU: {}",product.getId(),product.getSku());
        return saved;
    }


    //Get all Product
    public List<Product> getALlProduct() {
        List<Product> products = repository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Nothing exist into product");
        }
        return products;

    }


    //Get by Id
    public Product getProductById(Long id) {
        Product product= repository.findById(id).orElseThrow(()
                -> new ProductNotFoundException("Product Not found with id " + id));

        log.debug("Product retrived successfilly ID={}, SKU={}",product.getId(),product.getSku());

        return product;
    }


    public Product updateProduct(Long id, Product updated) {

        Product existing = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Exist for update  with id " + id));

        // Update name
        if (updated.getName() != null) {
            existing.setName(updated.getName());
        }

        // Update description
        if (updated.getDescription() != null) {
            existing.setDescription(updated.getDescription());
        }

        // Prevent SKU change
        if (updated.getSku() != null) {
            if (!existing.getSku().equals(updated.getSku())) {
                throw new SkuAlreadyExistException("SKU cannot be changed");
            }
        }

        // Update price
        if (updated.getPrice() != null) {
            existing.setPrice(updated.getPrice());
        }

        // Update quantity
        if (updated.getQuantity() != null) {
            existing.setQuantity(updated.getQuantity());
        }

        // Update status
        if (updated.getStatus() != null) {
            existing.setStatus(updated.getStatus());
        }

        return repository.save(existing);
    }



    //Delete Product
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }


}
