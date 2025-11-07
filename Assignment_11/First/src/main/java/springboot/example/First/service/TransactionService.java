package com.example.personalfinancetracker.service;

import com.example.personalfinancetracker.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private List<Transaction> transactions = new ArrayList<>();
    private int idCounter = 1;

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Optional<Transaction> getTransactionById(int id) {
        return transactions.stream().filter(t -> t.getId() == id).findFirst();
    }

    public Transaction addTransaction(Transaction transaction) {
        transaction.setId(idCounter++);
        transactions.add(transaction);
        return transaction;
    }

    public Optional<Transaction> updateTransaction(int id, Transaction updatedTransaction) {
        return getTransactionById(id).map(existing -> {
            existing.setTitle(updatedTransaction.getTitle());
            existing.setAmount(updatedTransaction.getAmount());
            existing.setType(updatedTransaction.getType());
            return existing;
        });
    }

    public boolean deleteTransaction(int id) {
        return transactions.removeIf(t -> t.getId() == id);
    }

    // Advanced: Filter by type
    public List<Transaction> getTransactionsByType(String type) {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}
