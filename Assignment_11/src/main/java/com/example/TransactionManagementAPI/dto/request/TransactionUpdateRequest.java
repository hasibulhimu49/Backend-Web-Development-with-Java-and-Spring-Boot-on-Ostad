package com.example.TransactionManagementAPI.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class TransactionUpdateRequest {

    @JsonProperty("t_title")
    private String title;

    @JsonProperty("t_amount")
    private double amount;

    @Size(max = 10)
    @JsonProperty("t_type")
    private String type;

    @JsonProperty("t_category")
    private String category;
}
