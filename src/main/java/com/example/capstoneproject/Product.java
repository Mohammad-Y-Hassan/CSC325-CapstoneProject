package com.example.capstoneproject;

public enum Product {

    GoldStandard("GoldStandard.png", 11.49, "GoldStandard", "Protein Powder"),
    CAMS("CAMS.png", 39.98, "CAMS", "Men’s Jacket"),
    SolDeJanerio("SolDeJanerio.png", 24.00, "Sol De Janerio", "Women’s Body Spray"),
    AXEDualBody("AXEDualBody.png", 21.96, "AXE Dual Body", "Men’s Body Spray"),
    Sceptre24inch("Sceptre 24-inch.png", 94.47, "Sceptre 24-inch", "Monitor"),
    AppleWatchSE("AppleWatchSE.png", 189.49, "Apple Watch SE", "Smart Watch"),
    iPhone15Plus("iPhone15Plus.png", 929.99, "iPhone 15 Plus", "Smart Phone"),
    RingDoorbellCam("RingDoorbellCam.png", 99.99, "Ring Doorbell Cam", "Security");


    private String imageFile;
    private double price;
    private String productName;
    private String productDescription;
    Product(String imageFile, double price, String productName, String productDescription) {
        this.imageFile = imageFile;
        this.price = price;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public String getImageFile() {
        return imageFile;
    }
}
