package com.example.capstoneproject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    private static final String FILE_PATH = "src/main/resources/products.json";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static StorageManager instance = new StorageManager();

    private StorageManager() {}

    public static StorageManager getInstance() {
        return instance;
    }

    public void saveProducts(List<SharedModel.Product> products) throws IOException {
        try {
            // Ensures that the directory exists before attempting to write to the file
            File file = Paths.get(FILE_PATH).toFile();
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs(); // Make directory if it does not exist
            }
            mapper.writeValue(file, products);
        } catch (IOException e) {
            System.out.println("Failed to save products: " + e.getMessage());
            throw e; // Rethrow if you need to handle this higher up
        }
    }

    public List<SharedModel.Product> loadProducts() throws IOException {
        File file = Paths.get(FILE_PATH).toFile();
        if (!file.exists()) {
            System.out.println("The file does not exist: " + FILE_PATH);
            return new ArrayList<>(); // Return an empty list if the file doesn't exist
        }
        try {
            // Reads the file into the list of products
            return mapper.readValue(file, new TypeReference<List<SharedModel.Product>>() {});
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            throw e; // Rethrow to handle the error appropriately in the calling code
        }
    }
}