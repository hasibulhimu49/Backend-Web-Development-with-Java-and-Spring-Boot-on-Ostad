package com.example.product_inventory_api;

import com.example.product_inventory_api.exception.ProductNotFoundException;
import com.example.product_inventory_api.model.entity.Product;
import com.example.product_inventory_api.repository.ProductRepository;
import com.example.product_inventory_api.service.ProductManagerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductManagerServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductManagerService productManagerService;

    // ------------ FIND PRODUCT TESTS --------------------

    @Test
    void testFindProductBySku_Success() {
        Product product = Product.builder()
                .sku("SKU-HASI")
                .quantity(20)
                .build();

        when(productRepository.findBySku("SKU-HASI"))
                .thenReturn(Optional.of(product));

        Product response = productManagerService.findProductBySku("SKU-HASI");

        assertEquals("SKU-HASI", response.getSku());
        assertEquals(20, response.getQuantity());
    }

    @Test
    void testFindProductBySku_NotFound() {

        when(productRepository.findBySku("INVALID-SKU"))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productManagerService.findProductBySku("INVALID-SKU")
        );
    }

    // ------------ RESTOCK PRODUCT TESTS --------------------

    @Test
    void testRestockProduct_Success() {
        Product product = Product.builder()
                .sku("SKU-REST")
                .quantity(20)
                .build();

        when(productRepository.findBySku("SKU-REST"))
                .thenReturn(Optional.of(product));

        when(productRepository.save(any(Product.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Product response = productManagerService.restockProduct("SKU-REST", 10);

        assertEquals(30, response.getQuantity()); // 20 + 10
        verify(productRepository, times(1)).save(response);
    }

    @Test
    void testRestockProduct_NotFound() {

        when(productRepository.findBySku("BAD-SKU"))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productManagerService.restockProduct("BAD-SKU", 10)
        );
    }
}