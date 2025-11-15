package com.productInventoryAPI.services;

import com.productInventoryAPI.entity.Product;
import com.productInventoryAPI.exception.InvalidSkuFormatException;
import com.productInventoryAPI.exception.ProductNotFoundException;
import com.productInventoryAPI.exception.SkuAlreadyExistsException;
import com.productInventoryAPI.repository.ProductRepository;
import com.productInventoryAPI.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private static final Pattern SKUPATTERN = Pattern.compile("^SKU-[A-Za-z0-9]{8}$");

    private void validateSkuFormatOrThrow(String sku) {
        if (sku == null || !SKUPATTERN.matcher(sku).matches()) {
            throw new InvalidSkuFormatException("SKU must be in format 'SKU-XXXXXXXX' where X is alphanumeric (8 characters). Given: " + sku);
        }
    }
    @Override
    public ApiResponse<Product> createProduct(Product product) {
        validateSkuFormatOrThrow(product.getSku());

        if(productRepository.existsBySku(product.getSku())){
            log.warn("Product with sku {} already exists", product.getSku());
            throw new SkuAlreadyExistsException("Product with sku " + product.getSku() + " already exists");
        }
        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setSku(product.getSku());
        product1.setQuantity(product.getQuantity());
        product1.setDescription(product.getDescription());
        product1.setStatus(product.getStatus());
        Product savedProduct = productRepository.save(product1);
        log.info("Created product with id: {} and sku: {}", product1.getId(), product1.getSku());
        ApiResponse<Product> response = new ApiResponse<>(
                HttpStatus.CREATED.toString(),
                201,
                "Product created successfully.",
                false,
                savedProduct
        );
        return response;
    }

    @Override
    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()){
            log.warn("Failed to find any product ");
            throw new ProductNotFoundException("Product list is empty");
        }
        log.info("Returned response to all products: {}", productList);
        ApiResponse<List<Product>> response = new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                "Returned All Products",
                false,
                productList
        );
        return response;
    }

    @Override
    public ApiResponse<Product> getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findProductById(id);
        if(productOptional.isEmpty()){
            log.warn("Failed to find product with ID: {}", id);
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        Product product = productOptional.get();
        log.info("Returned response to product with id: {}", product.getId());
        ApiResponse<Product> response = new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                "Returned All Products",
                false,
                product
        );
        return response;
    }

    @Override
    public ApiResponse<Product> updateProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findProductById(id);
        if(productOptional.isEmpty()){
            log.warn("Failed to find product with ID: {}", id);
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        Product updatedProduct = productOptional.get();
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setSku(product.getSku());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantity(product.getQuantity());
        updatedProduct.setStatus(product.getStatus());
        productRepository.save(updatedProduct);
        log.info("Updated product with ID: {} and new SKU: {}", updatedProduct.getId(), updatedProduct.getSku());
        ApiResponse<Product> response = new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                "Product Updated Successfully",
                false,
                updatedProduct
        );
        return response;
    }

    @Override
    public void deleteProductById(Long id) {
        Optional<Product> productOptional = productRepository.findProductById(id);
        if(productOptional.isEmpty()){
            log.warn("Failed to find product with ID: {}", id);
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
        log.info("Deleted product with id : {}", id);

    }
}
