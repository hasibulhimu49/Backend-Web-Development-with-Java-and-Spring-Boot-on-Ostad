package com.example.product_inventory_api.repository;

import com.example.product_inventory_api.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsBySku(String sku);
    Optional<Product> findBySku(String sku);
}
