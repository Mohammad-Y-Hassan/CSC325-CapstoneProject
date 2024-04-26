package com.example.capstoneproject;

// Used for validating email
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
// import jdk.internal.access.JavaNioAccess;

public class CreateAccountController {
    @FXML
    private Button LogInButton2;

    @FXML
    private Button MainCreateAccountButton;

    @FXML
    private Button MainLogInButton;

    @FXML
    private Button CreateAccountButton2;

    @FXML
    private PasswordField PasswordTextField;


    @FXML
    private TextField FirstNameTextField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private Label LogInMessageLabel;


    // Validates email
    public boolean isValidEmail(String email) {
        // Regular expression pattern for email validation
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Create a Pattern object
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(email);

        // Check if the email matches the pattern
        return matcher.matches();
    }

    public void logInUser(UserRecord newUserRecord, String firstName, String email, ActionEvent event) {

    }

    public void createUser(String email, String password, String firstName, ActionEvent event) {
        Firestore db = FirebaseContext.getFirestore();
        if (db == null) {
            System.err.println("Firestore is not initialized");
            return;
        }
        Map<String, Object> User = new HashMap<>();
        User.put("Email Address", email);   // Matching field names from your Firestore screenshot
        User.put("Full Name", firstName);   // Matching field names from your Firestore screenshot
        User.put("Password", password);     // Matching field names from your Firestore screenshot

        DocumentReference docRef = db.collection("User").document(email);
//        ApiFuture<WriteResult> future = db.collection("User").document(email).set(user);
        ApiFuture<WriteResult> future = docRef.set(User);
        try {
            WriteResult result = future.get(); // This will block and wait for the operation to complete
            System.out.println("User added successfully, update time: " + result.getUpdateTime());
        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public void HomeView(ActionEvent event) throws IOException {
        String firstName = FirstNameTextField.getText();
        String email = EmailTextField.getText();
        String password = PasswordTextField.getText();

        if (firstName.length() >= 3 && isValidEmail(email) && email.endsWith("@gmail.com") && password.length() >= 8) {
            createUser(email, password, firstName, event);
            navigateToHome(event);
        } else {
            LogInMessageLabel.setText("Please enter valid credentials");
        }
    }

    public void LogIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/logIn.fxml"));

        // Set the root element type programmatically
        BorderPane root = new BorderPane();
        loader.setRoot(root);

        // Now load the FXML
        loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);


    }
    public void CreateAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/CreateAccount.fxml"));

        // Set the root element type programmatically
        BorderPane root = new BorderPane();
        loader.setRoot(root);

        // Now load the FXML
        loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
    }

    public void navigateToHome(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/HomeView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
    }
}
