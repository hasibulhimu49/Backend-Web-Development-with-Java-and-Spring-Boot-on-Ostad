package com.taransactionmanagement.TMAPI.model;

import lombok.Data;

@Data
public class Transaction {
    private int id;
    private String name;
    private int amount;
    private String type;
}
