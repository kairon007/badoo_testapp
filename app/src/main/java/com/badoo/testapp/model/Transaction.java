package com.badoo.testapp.model;

import java.io.Serializable;

/**
 * A transaction for a product
 */
public class Transaction implements Serializable{
    private String sku;
    private float amount;
    private String currency;

    public Transaction(String sku, float amount, String currency) {
        this.sku = sku;
        this.amount = amount;
        this.currency = currency;
    }

    public String getSku() {
        return sku;
    }

    public float getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
