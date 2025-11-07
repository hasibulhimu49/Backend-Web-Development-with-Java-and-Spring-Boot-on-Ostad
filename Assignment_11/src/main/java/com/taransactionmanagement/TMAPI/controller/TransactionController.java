package com.taransactionmanagement.TMAPI.controller;

import com.taransactionmanagement.TMAPI.model.Transaction;
import com.taransactionmanagement.TMAPI.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public Transaction addTransactions(@RequestBody Transaction transaction)
    {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransaction()
    {
        return transactionService.getAllTransaction();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int id)
    {
        return transactionService.getTransactionById(id);

    }

    @PutMapping("/{id}")
    public Transaction updatedTransaction(@PathVariable int id,@RequestBody Transaction transaction)
    {
      return transactionService.updateTransaction(id,transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id)
    {
        return transactionService.deleteTransaction(id);
    }

    @GetMapping(params = "type")
    public List<Transaction> getTransactionByType(@RequestParam("type") String type)
    {
         return  transactionService.getTransactionByType(type);
    }
}
