package com.example.TransactionManagementAPI.controller;

import com.example.TransactionManagementAPI.dto.request.TransactionCreateRequest;
import com.example.TransactionManagementAPI.dto.request.TransactionUpdateRequest;
import com.example.TransactionManagementAPI.dto.response.TransactionResponse;
import com.example.TransactionManagementAPI.entity.Transaction;
import com.example.TransactionManagementAPI.mapper.TransactionMapper;
import com.example.TransactionManagementAPI.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionResponse createTransaction(@RequestBody TransactionCreateRequest transactionCreateRequest)
    {
      Transaction transaction=TransactionMapper.toEntity(transactionCreateRequest);
      Transaction savedTransaction=transactionService.createTransaction(transaction);
      return TransactionMapper.toDto(transaction);


    }

    @GetMapping("/{id}")
    public TransactionResponse getTransactionById(@PathVariable Long id)
    {
        Transaction transaction = transactionService.getTransactionById(id);
        return TransactionMapper.toDto(transaction);

    }

    @GetMapping
    public List<TransactionResponse> getAllTransaction()
    {
        List<Transaction> transactions= transactionService.getAllTransaction();
        return transactions.stream().map(transaction -> TransactionMapper.toDto(transaction)).toList();
    }

    @PutMapping("/{id}")
    public TransactionResponse updateTransaction(@PathVariable Long id, @RequestBody TransactionUpdateRequest transactionUpdateRequest)
    {
        Transaction transaction=transactionService.updateTransaction(id,transactionUpdateRequest);
        return TransactionMapper.toDto(transaction);


    }

    @DeleteMapping("/{id}")
    public String updateTransaction(@PathVariable Long id)
    {
        boolean deleted=transactionService.deleteTransaction(id);
        return deleted ? "Deleted Successfully": "Not found";
    }


}
