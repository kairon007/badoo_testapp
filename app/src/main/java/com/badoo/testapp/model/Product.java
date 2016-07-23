package com.badoo.testapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A derived model to group transactions1
 */
public class Product implements Serializable{
    private String sku;
    List<Transaction> transactions = new ArrayList<>();

    public Product(String sku){
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
}
