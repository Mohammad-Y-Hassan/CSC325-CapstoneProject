package com.example.capstoneproject;

// Used for validating email
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.firebase.auth.FirebaseAuth;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
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
        // Revised regular expression pattern for email validation
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$";

        // Create a Pattern object
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(email);

        // Check if the email matches the pattern
        boolean matches = matcher.matches();

        return matches;
    }

    public void logInUser(UserRecord newUserRecord, String firstName, String email, ActionEvent event) {

    }

    public void createUser(String email, String password, String firstName, ActionEvent event) {
        Firestore db = FirebaseContext.getFirestore();

        if (db == null) {
            System.err.println("Firestore is not initialized");
            return;
        }

        // Prepare user data
        Map<String, Object> user = new HashMap<>();
        user.put("Email Address", email);
        user.put("Full Name", firstName);
        user.put("Password", password);

        DocumentReference docRef = db.collection("User").document(email);

        ApiFuture<WriteResult> apiFuture = docRef.set(user);

        CompletableFuture<WriteResult> future = toCompletableFuture(apiFuture);

        future.thenAccept(result -> {
            System.out.println("Document created at: " + result.getUpdateTime());
        }).exceptionally(ex -> {
            System.err.println("Error setting document: " + ex.getMessage());
            return null;
        });
    }

    private <T> CompletableFuture<T> toCompletableFuture(ApiFuture<T> apiFuture) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();

        apiFuture.addListener(() -> {
            try {
                completableFuture.complete(apiFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                completableFuture.completeExceptionally(e);
            }
        }, Executors.newSingleThreadExecutor());

        return completableFuture;
    }

    public void HomeView(ActionEvent event) throws IOException {
        String firstName = FirstNameTextField.getText();
        String email = EmailTextField.getText();
        String password = PasswordTextField.getText();

        List<String> allowedDomains = Arrays.asList("@gmail.com", "@yahoo.com", "@farmingdale.edu"); // Add more as needed

        boolean isAllowedDomain = allowedDomains.stream().anyMatch(domain -> email.endsWith(domain));

        if (firstName.length() >= 3 && isValidEmail(email) && isAllowedDomain && password.length() >= 8) {
            createUser(email, password, firstName, event);
            navigateToHome(event);
        } else {
            LogInMessageLabel.setText("Please enter valid credentials");
        }
    }

    public void LogIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/logIn.fxml"));
        BorderPane root = new BorderPane();
        loader.setRoot(root);
        loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    public void CreateAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/CreateAccount.fxml"));
        BorderPane root = new BorderPane();
        loader.setRoot(root);
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
