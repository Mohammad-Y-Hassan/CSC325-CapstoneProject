package com.example.capstoneproject;

public class SharedModel {
    private String firstName;
    private String email;

    private static final SharedModel instance = new SharedModel();
    private static final SharedModel instance2 = new SharedModel();

    private SharedModel() { }

    public static SharedModel getInstance() {
        return instance;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

}
