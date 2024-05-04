package com.example.capstoneproject;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> items;
    public Cart(){
        this.items = new ArrayList<Item>();
    }
    public void addItem(Item item){
        items.add(item);
    }
    public void removeItem(Item item){
        items.remove(item);
    }
    //getter for item
    public List<Item> getItems(){
        return items;
    }
}
