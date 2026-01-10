package com.example.TransactionManagementAPI.entity;

import lombok.Data;

@Data
public class Transaction {
    private Long id;
    private String title;
    private double amount;
    private String type;
    private String category;
}
