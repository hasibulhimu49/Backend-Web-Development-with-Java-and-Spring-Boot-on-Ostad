package com.example.TransactionManagementAPI.dto.response;

import lombok.Data;

@Data
public class TransactionResponse {
    private Long id;
    private String  title;
    private double amount;
    private String type;
    private String category;
}
