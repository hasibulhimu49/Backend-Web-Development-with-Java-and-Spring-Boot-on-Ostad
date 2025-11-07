package com.example.personalfinancetracker.model;

public class Transaction {
    private int id;
    private String title;
    private double amount;
    private String type; // "income" or "expense"

    public Transaction() {}

    public Transaction(int id, String title, double amount, String type) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.type = type;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
