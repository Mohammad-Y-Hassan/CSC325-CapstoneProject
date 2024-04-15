package com.example.capstoneproject;

// Used for validating email
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
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

    public void HomeView(ActionEvent event) throws IOException {
        String firstName = FirstNameTextField.getText();
        String email = EmailTextField.getText();
        String password = PasswordTextField.getText();

        if (firstName.length() >= 3 && isValidEmail(email) && email.endsWith("@gmail.com") && password.length() >= 8) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/HomeView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
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
}
