package com.example.capstoneproject;

// Used for validating email
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class CreateAccountController {

    //@FXML
    //private Button CreateAccountButton;

    @FXML
    private PasswordField PasswordTextField;

    @FXML
    private Button LogInButton;

    @FXML
    private TextField FirstNameTextField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private Label LogInMessageLabel;

    // Validates email
    public boolean isValidEmail(String email){
        // Regular expression pattern for email validation
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Create a Pattern object
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(email);

        // Check if the email matches the pattern
        return matcher.matches();
    }
    public void CreateAccountButton(ActionEvent event) throws IOException{
        String firstName = FirstNameTextField.getText();
        String email = EmailTextField.getText();
        String password = PasswordTextField.getText();

        if (firstName.length() >= 3 && isValidEmail(email) && email.endsWith("@gmail.com") && password.length() > 8) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/HomeView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
        } else {
            LogInMessageLabel.setText("Please enter valid credentials");
        }
    }

}
