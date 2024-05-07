package com.example.capstoneproject;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<SharedModel.Product> items = new ArrayList<>();
    private double subtotal = 0.0;
    private final double TAX_RATE = 0.067; // 6.7%

    public void addItem(SharedModel.Product item) {
        if (item.getPrice() != null && !item.getPrice().isEmpty()) {
            String priceStr = item.getPrice().replaceAll("[^\\d.]", ""); // Remove non-numeric characters
            double price = Double.parseDouble(priceStr);
            subtotal += price;
        }
        items.add(item);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTax() {
        return subtotal * TAX_RATE;
    }

    public double getTotal() {
        return subtotal + getTax();
    }

    public List<SharedModel.Product> getItems() {
        return items;
    }

    public boolean isCartEmpty() {
        return items.isEmpty();
    }
}