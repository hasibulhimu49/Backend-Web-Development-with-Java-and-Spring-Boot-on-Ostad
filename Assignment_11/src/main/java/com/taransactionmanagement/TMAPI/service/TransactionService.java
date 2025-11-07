package com.taransactionmanagement.TMAPI.service;

import com.taransactionmanagement.TMAPI.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private List<Transaction> transactionList=new ArrayList<>();
    private int idCount=1;

    public Transaction addTransaction(Transaction transaction)
    {
        transaction.setId(idCount++);
        transactionList.add(transaction);
        return transaction;
    }

    public List<Transaction> getAllTransaction()
    {
        return transactionList;
    }

    public ResponseEntity<Transaction> getTransactionById(int id)
    {
     Transaction transaction =  transactionList.stream()
                               .filter(tr->tr.getId()==id)
                               .findFirst().orElse(null);
         return ResponseEntity.ok(transaction);

    }

    public Transaction updateTransaction(int id,Transaction updatedTrans)
    {
        Transaction existTrans =  transactionList.stream()
                .filter(tr->tr.getId()==id)
                .findFirst().orElse(null);

        updatedTrans.setId(id);
        existTrans.setName(updatedTrans.getName());
        existTrans.setType(updatedTrans.getType());
        existTrans.setAmount(updatedTrans.getAmount());

        return updatedTrans;
    }


    public ResponseEntity<String> deleteTransaction(int id)
    {
        Transaction existTrans =  transactionList.stream()
                .filter(tr->tr.getId()==id)
                .findFirst().orElse(null);

       if(existTrans!=null)
       {
           transactionList.remove(existTrans);
           return ResponseEntity.ok("DELete success");
       }
       else
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not DEleted");

    }



    public List<Transaction> getTransactionByType(String Type)
    {
          return transactionList.stream()
                  .filter(tp->tp.getType().equalsIgnoreCase(Type)).toList();

    }



}
