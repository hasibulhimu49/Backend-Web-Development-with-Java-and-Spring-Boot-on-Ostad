package com.productInventoryAPI.entity;

import com.productInventoryAPI.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products",uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Product name must not be blank.")
    private String name;

    @Size(max = 500, message = "Description must be no longer than 500 characters")
    private String description;

    @NotBlank(message = "SKU must not be blank.")
    private String sku;

    @NotNull(message = "Price must not be null.")
    @Positive(message = "Price must be a positive number.")
    private Double price;

    @NotNull(message = "Quantity must not be null.")
    @Min(value = 0, message = "Quantity must be zero or more.")
    private Integer quantity;

    @NotNull(message = "Status must not be null.")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

}
