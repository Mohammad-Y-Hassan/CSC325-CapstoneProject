package com.example.capstoneproject;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.example.capstoneproject.SharedModel.Product;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class UploadPostController extends SceneLoader{
    @FXML
    private ImageView uploadPostPic;

    @FXML
    private Button fullUploadPostButton;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField sellerEmailTextField;

    @FXML
    private Label missingInfoTextField;

    @FXML
    public void initialize() {
        sellerEmailTextField.setText(SharedModel.getInstance().getEmail());
        // Call formatPrice when the TextField loses focus
        priceTextField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                formatPrice();
            }
        });
    }

    public void returntoHome(MouseEvent event) throws IOException {
        toHomeView(event);
    }
    public void toProfile(MouseEvent event) throws IOException{
        toProfileView(event);
    }
    public void toCart(MouseEvent event) throws IOException {
        toCartView(event);
    }

    //Add an option to upload an image
    public void uploadpostPic(MouseEvent event)throws IOException{
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String imagePath = file.toURI().toString();
            SharedModel.getInstance().setImageURI(imagePath);  // Store URI instead of Image
            Image image = new Image(imagePath);
            uploadPostPic.setImage(image);  // Set image in ImageView
        }
    }

    public void uploadPost(ActionEvent event) throws IOException {
        String title = titleTextField.getText();
        String price = priceTextField.getText();
        String email = sellerEmailTextField.getText();
        String imageURI = SharedModel.getInstance().getImageURI(); // Get the image URI from SharedModel

        if (checkRequiredFields()) { // Check all required fields before proceeding
            Product newProduct = new Product(imageURI, title, price);
            SharedModel.getInstance().addProduct(newProduct);
            StorageManager.getInstance().saveProducts(SharedModel.getInstance().getProducts()); // Corrected to use singleton
            addProduct(imageURI, price, email, title);
            missingInfoTextField.setStyle("-fx-text-fill: green;"); // Set the text color to green
            missingInfoTextField.setText("Post was successfully uploaded");
        }
    }
    private void addProduct(String imageURI, String price, String email, String title) {

        String documentId = UUID.randomUUID().toString();  // Unique ID for each product
        DocumentReference docRef = FirebaseContext.getFirestore().collection("Products").document(documentId);
        Map<String, Object> data = new HashMap<>();
        data.put("imageURI", imageURI);
        data.put("price", price);
        data.put("sellerEmail", email);
        data.put("title", title);

        // Set data in Firestore
        ApiFuture<WriteResult> result = docRef.set(data);
        try {
            WriteResult writeResult = result.get();  // Wait for the operation to complete
            System.out.println("Product created with ID: " + documentId + ", at time: " + writeResult.getUpdateTime());
            // Update UI or navigate as necessary
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error adding product to Firestore: " + e.getMessage());
            Thread.currentThread().interrupt();  // Re-interrupt the current thread if InterruptedException
        }
    }

    public void formatPrice() {
        // Get current text from the priceTextField
        String text = priceTextField.getText();

        // Remove any non-digit characters (except decimal points)
        text = text.replaceAll("[^\\d]", "");

        // Check if the text can be parsed to a double, then format as a currency
        try {
            double price = Double.parseDouble(text);
            // Format the number as a currency with a dollar symbol
            priceTextField.setText("$ " + String.format("%.2f", price));
        } catch (NumberFormatException e) {
            priceTextField.setText("$0.00");
        }
    }
    public boolean checkRequiredFields() {
        boolean hasImage = SharedModel.getInstance().getImageURI() != null && !SharedModel.getInstance().getImageURI().isEmpty();
        boolean hasPrice = !priceTextField.getText().isEmpty();
        boolean hasTitle = !titleTextField.getText().isEmpty();
        boolean hasEmail = !sellerEmailTextField.getText().isEmpty();

        StringBuilder missingInfo = new StringBuilder("Missing: ");
        int missingCount = 0;

        if (!hasImage) {
            missingInfo.append("Image, ");
            missingCount++;
        }
        if (!hasPrice) {
            missingInfo.append("Price, ");
            missingCount++;
        }
        if (!hasTitle) {
            missingInfo.append("Title, ");
            missingCount++;
        }
        if (!hasEmail) {
            missingInfo.append("Seller Email, ");
            missingCount++;
        }

        if (missingCount > 0) {
            missingInfoTextField.setText(missingInfo.substring(0, missingInfo.length() - 2)); // Remove the last comma and space
            return false;
        }

        return true;
    }
}


