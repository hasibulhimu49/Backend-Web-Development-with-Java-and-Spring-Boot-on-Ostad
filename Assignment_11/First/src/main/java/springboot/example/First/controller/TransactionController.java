package com.example.personalfinancetracker.controller;

import com.example.personalfinancetracker.model.Transaction;
import com.example.personalfinancetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    // 1. Get all transactions
    @GetMapping
    public List<Transaction> getAll() {
        return service.getAllTransactions();
    }

    // 2. Get a specific transaction by id
    @GetMapping("/{id}")
    public Transaction getById(@PathVariable int id) {
        return service.getTransactionById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    // 3. Add new transaction
    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }

    // 4. Update specific transaction
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable int id, @RequestBody Transaction transaction) {
        return service.updateTransaction(id, transaction)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    // 5. Delete specific transaction
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable int id) {
        boolean removed = service.deleteTransaction(id);
        return removed ? "Transaction deleted" : "Transaction not found";
    }

    // Advanced (optional)
    @GetMapping(params = "type")
    public List<Transaction> getByType(@RequestParam String type) {
        return service.getTransactionsByType(type);
    }
}
