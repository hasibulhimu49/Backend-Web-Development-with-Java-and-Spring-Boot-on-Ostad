package com.example.product_inventory_api.model.entity;


import com.example.product_inventory_api.model.base.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_table")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Must not be blank.")
    String name;

    @Size(max = 500)
    String description;

    @NotNull(message = "Must not be blank.")
    //@Pattern(regexp ="^SKU-[A-Za-z0-9]{8}$",message = "Invalid SKU Format")
    @Column(unique = true)
    String sku; //Stock keeping Quantity like "TSHIRT-BLUE-M"

    @Positive(message = "Must not be null and must be a positive number")
    Double price;

    @NotNull(message = "Must not be null and must be zero or more")
    @Min(0)
    Integer quantity;

    @NotNull(message = "Must not be null")
    @Enumerated(EnumType.STRING)
    ProductStatus status;

}
