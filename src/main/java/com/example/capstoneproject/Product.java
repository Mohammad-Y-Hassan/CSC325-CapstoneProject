package com.example.capstoneproject;

public enum Product {

    GoldStandard("GoldStandard.png", 11.49),
    CAMS("CAMS.png", 39.98),
    SolDeJanerio("SolDeJanerio.png", 24.00),
    AXEDualBody("AXEDualBody.png", 21.96),
    Sceptre24inch("Sceptre 24-inch.png", 94.47),
    AppleWatchSE("AppleWatchSE.png", 189.49),
    iPhone15Plus("iPhone15Plus.png", 929.99),
    RingDoorbellCam("RingDoorbellCam.png", 99.99);


    private String imageFile;
    private double price;

    Product(String imageFile, double price) {
        this.imageFile = imageFile;
        this.price = price;
    }

    public String getImageFile() {
        return imageFile;
    }
    public double getPrice() {
        return price;
    }
}
