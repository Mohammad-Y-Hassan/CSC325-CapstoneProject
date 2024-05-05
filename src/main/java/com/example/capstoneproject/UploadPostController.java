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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    private Firestore firestore = FirebaseContext.getFirestore();

    @FXML
    public void initialize() {
        sellerEmailTextField.setText(SharedModel.getInstance().getEmail());
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
        //sets extension filters
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.gif)", "*.png", "*.jpg","*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        //open file dialog
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            Image image = new Image(file.toURI().toString());
            uploadPostPic.setImage(image);
        }
    }

    private void uploadPost(ActionEvent event) throws IOException {
        String title = titleTextField.getText();
        String price = priceTextField.getText();
        String email = sellerEmailTextField.getText();

        addProduct(title, price, email);
    }
    private void addProduct(String price, String email, String title) {
        String documentId = UUID.randomUUID().toString();  // Unique ID for each product
        DocumentReference docRef = FirebaseContext.getFirestore().collection("Products").document(documentId);
        Map<String, Object> data = new HashMap<>();
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

    }
}


