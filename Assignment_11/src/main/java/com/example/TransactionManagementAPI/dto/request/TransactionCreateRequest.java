package com.example.TransactionManagementAPI.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class TransactionCreateRequest {

    @JsonProperty("t_title")
    private String title;

    @NotBlank(message = "Amount must be add")
    @JsonProperty("t_amount")
    private double amount;

    @Size(max = 10)
    @JsonProperty("t_type")
    private String type;

    @JsonProperty("t_category")
    private String category;
}
