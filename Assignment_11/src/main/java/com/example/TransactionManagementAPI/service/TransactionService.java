package com.example.TransactionManagementAPI.service;

import com.example.TransactionManagementAPI.dto.request.TransactionUpdateRequest;
import com.example.TransactionManagementAPI.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final List<Transaction> transactions=new ArrayList<>();
    private Long idGenerate=1L;


    //Create
    public Transaction createTransaction(Transaction transaction)
    {
        transaction.setId(idGenerate++);
        transactions.add(transaction);
        return transaction;
    }

    //get by id
    public Transaction getTransactionById(Long id)
    {
        return  transactions.stream().
                filter(transactions ->transactions.getId().equals(id)).
                findFirst().orElse(null);
    }

    //get all
    public List<Transaction> getAllTransaction()
    {
        return transactions;
    }

    //update Transaction
    public Transaction updateTransaction(Long id, TransactionUpdateRequest updateTransaction)
    {
       for(Transaction t:transactions)
       {
           if(t.getId().equals(id))
           {
               System.out.println("Before Update: "+t.getTitle());
               t.setTitle(updateTransaction.getTitle());
               System.out.println("After update: "+t.getTitle());
               t.setAmount(updateTransaction.getAmount());
               t.setType(updateTransaction.getType());
               t.setCategory(updateTransaction.getCategory());
               return t;
           }
       }
       return null;
    }

    //delete Transaction
    public boolean deleteTransaction(Long id)
    {
        return transactions.removeIf(transaction -> transaction.getId().equals(id));
    }
}
