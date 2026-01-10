package com.example.TransactionManagementAPI.mapper;

import com.example.TransactionManagementAPI.dto.request.TransactionCreateRequest;
import com.example.TransactionManagementAPI.dto.request.TransactionUpdateRequest;
import com.example.TransactionManagementAPI.dto.response.TransactionResponse;
import com.example.TransactionManagementAPI.entity.Transaction;

public class TransactionMapper {

    public static Transaction toEntity(TransactionCreateRequest transactionCreateRequest)
    {
        Transaction transaction=new Transaction();

        transaction.setTitle(transactionCreateRequest.getTitle());
        transaction.setAmount(transactionCreateRequest.getAmount());
        transaction.setType(transactionCreateRequest.getType());
        transaction.setCategory(transactionCreateRequest.getCategory());
        return transaction;
    }

    public static TransactionResponse toDto(Transaction transaction)
    {
        TransactionResponse transactionResponse=new TransactionResponse();
        transactionResponse.setId(transaction.getId());
        transactionResponse.setType(transaction.getType());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setTitle(transaction.getTitle());
        transactionResponse.setCategory(transaction.getCategory());
        return transactionResponse;
    }

    public static void updateEntity(TransactionUpdateRequest transactionUpdateRequest, Transaction transaction)
    {
        transaction.setTitle(transactionUpdateRequest.getTitle());
        transaction.setAmount(transactionUpdateRequest.getAmount());
        transaction.setType(transactionUpdateRequest.getType());
        transaction.setCategory(transactionUpdateRequest.getCategory());
    }
}
