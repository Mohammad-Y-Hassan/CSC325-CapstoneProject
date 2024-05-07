package com.example.capstoneproject;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SharedModel {
    private String firstName;
    private String email;
    private String imageURI;
    private List<Product> products = new ArrayList<>();
    private List<Runnable> listeners = new ArrayList<>();
    private static final SharedModel instance = new SharedModel();
    private Cart cart = new Cart();
    private List<Consumer<Cart>> cartListeners = new ArrayList<>();
    private SharedModel() { }

    // Singleton access
    public static SharedModel getInstance() {
        return instance;
    }

    public void addListener(Runnable listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }

    // Getters and setters
    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cart getCart() {
        return cart;
    }

    // Product class to store product details
    public static class Product {
        private String imageURI;
        private String title;
        private String price;

        // No-argument constructor
        public Product() {}

        // All-arguments constructor
        public Product(String imageURI, String title, String price) {
            this.imageURI = imageURI;
            this.title = title;
            this.price = price;
        }

        // Getters and setters
        public String getImageURI() {
            return imageURI;
        }

        public void setImageURI(String imageURI) {
            this.imageURI = imageURI;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    // Method to add a product and notify listeners
    public void addProduct(Product product) {
        products.add(product);
        notifyListeners();
        notifyCartListeners();
    }
    // Method to get all products
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
    public void setProducts(List<Product> newProducts) {
        this.products = new ArrayList<>(newProducts);
        notifyListeners();
    }
    public void addCartListener(Consumer<Cart> listener) {
        cartListeners.add(listener);
    }
    public void notifyCartListeners() {
        cartListeners.forEach(listener -> listener.accept(cart));
    }

}
