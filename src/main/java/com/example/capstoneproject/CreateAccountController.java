package com.example.capstoneproject;

// Used for validating email
import java.io.IOException;
import java.util.*;
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
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.internal.FirebaseRequestInitializer;
import javafx.application.Platform;
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
    @FXML private Button createAccountButton;
    @FXML private PasswordField PasswordTextField;
    @FXML private TextField FirstNameTextField,EmailTextField;
    @FXML private Label LogInMessageLabel;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$");
    private static final List<String> ALLOWED_DOMAINS = Arrays.asList("@gmail.com", "@yahoo.com", "@farmingdale.edu");

    private FirebaseAuth firebaseAuth = FirebaseContext.getFirebaseAuth();
    private Firestore firestore = FirebaseContext.getFirestore();

    // Validates email using a regular expression
    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // Attempts to create a user with Firebase
    private void createUser(ActionEvent event) {
            // Assume we're setting up a new user with email and password
            String email = EmailTextField.getText();
            String password = PasswordTextField.getText();
            String displayName = FirstNameTextField.getText();  // Assuming there's a field to get the user's name

            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisplayName(displayName);

            try {
                UserRecord userRecord = FirebaseContext.getFirebaseAuth().createUser(request);
                addData(userRecord.getUid(), displayName, email, password);
                System.out.println("User created with UID: " + userRecord.getUid());
                System.out.println("User created successfully with UID: " + userRecord.getUid());
                // You may now navigate or update UI
            } catch (FirebaseAuthException e) {
                System.err.println("Firebase Auth error: " + e.getMessage());
                System.out.println("Failed to create user: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
                System.out.println("Unexpected error: " + e.getMessage());
            }
    }

    public void logInUser(ActionEvent event) throws IOException{
        String firstName = FirstNameTextField.getText();
        String email = EmailTextField.getText();
        String password = PasswordTextField.getText();
        // Query Firestore database for user with matching email and password
        ApiFuture<QuerySnapshot> future = firestore.collection("Users")
                .whereEqualTo("email", email)
                .whereEqualTo("name", firstName)
                .whereEqualTo("password", password)
                .get();

        future.addListener(() -> {
            try {
                QuerySnapshot querySnapshot = future.get();
                if (!querySnapshot.isEmpty()) {
                    // User found
                    Platform.runLater(() -> {
                        System.out.println("Login successful!");
                        // Navigate to another view or update the scene, depending on your application design
                        try {
                            navigateToHome(event);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else {
                    // User not found
                    Platform.runLater(() -> LogInMessageLabel.setText("Login failed: Invalid credentials."));
                }
            } catch (InterruptedException | ExecutionException e) {
                Platform.runLater(() -> System.out.println("Failed to query the database: " + e.getMessage()));
            }
        }, Platform::runLater);
    }

    public void HomeView(ActionEvent event) throws IOException {
        String firstName = FirstNameTextField.getText();
        String email = EmailTextField.getText();
        String password = PasswordTextField.getText();

        List<String> allowedDomains = Arrays.asList("@gmail.com", "@yahoo.com", "@farmingdale.edu");
        boolean isAllowedDomain = allowedDomains.stream().anyMatch(domain -> email.endsWith(domain));

        if (firstName.length() >= 3 && isValidEmail(email) && isAllowedDomain && password.length() >= 8) {
            navigateToHome(event);
            createUser(event);

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

    public void navigateToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/HomeView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private void addData(String userId, String name, String email, String password) {
        DocumentReference docRef = firestore.collection("Users").document(userId);
        Map<String, Object> data = new HashMap<>();
        data.put("email", email);
        data.put("name", name);
        data.put("password", password);
        docRef.set(data).addListener(() -> LogInMessageLabel.setText("Data written to Firestore successfully."),
                executor -> LogInMessageLabel.setText("Error writing to Firestore."));
    }

}