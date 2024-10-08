package com.example.capstoneproject;

import javafx.application.Platform;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserCartController extends SceneLoader{

    /**picture, item name and price of item variables**/
    @FXML
    private ImageView itempicInCart;
    @FXML
    private Label cardErrorLabel;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField ccvTextField;
    @FXML
    private TextField expDateTextField;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField addressTextField;

    @FXML
    private Text subtotalInCart;
    @FXML
    private Text taxesInCart;
    @FXML
    private Text totalInCart;
    @FXML
    private Text missingItemText;
    @FXML
    private Button orderButton;
    @FXML
    private GridPane productGridPane;

    @FXML
    private void initialize() {
        updateCartDisplay(); // Initial display setup
        SharedModel.getInstance().addListener(this::updateCartDisplay); // Listen for changes
        fullNameTextField.setText(SharedModel.getInstance().getFirstName());

        ccvTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            formatCcv(oldValue, newValue);
        });

        expDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            formatExpDate(oldValue, newValue);
        });

        cardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            formatCardNumber(oldValue, newValue);
        });

        addressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateCardDetails();
        });

        fullNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateCardDetails();
        });

        orderButton.setOnAction(event -> processOrder());
    }

    private Text sampleItemNameInCart;
    @FXML
    private Text samplePriceInCart;



    public void returntoHome(MouseEvent event) throws IOException {
        toHomeView(event);
    }
    public void toProfile(MouseEvent event) throws IOException {
        toProfileView(event);
    }
    public void uploadPost(MouseEvent event) throws IOException {
        toUploadPostView(event);
    }
    public void toCart(MouseEvent event) throws IOException {
        toCartView(event);
    }

    public boolean validExpDate(String date) {
        // Regex to check for MM/YY format
        if (!date.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            return false;
        }

        // Split the date string into month and year
        String[] parts = date.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);

        // Convert the year into full year format (assumes 2000s)
        year += 2000;

        // Define the threshold month and year
        int thresholdYear = 2024;
        int thresholdMonth = 5;  // May 2024 is the last invalid month

        // Check if the year is greater than the threshold year
        if (year > thresholdYear) {
            return true;
        } else if (year == thresholdYear) {
            // If the year is the threshold year, check the month
            return month > thresholdMonth;
        }

        return false;
    }

    public boolean validCcv(String num) {
        String digitsOnly = num.replaceAll("[^\\d]", "");  // Removes non-digit characters
        return digitsOnly.length() == 3 && digitsOnly.equals(num);
    }

    public void CardVerifier() {
        // Listener for card number formatting and validation
        cardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            formatCardNumber(oldValue, newValue);
        });

        // Listener for checking if the expiration date is valid
        expDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            formatCcv(oldValue, newValue);
        });

        // Listener for checking if the CCV is valid
        ccvTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateCardDetails();
        });
    }

    public void formatCardNumber(String oldValue, String newValue) {
        String digitsOnly = newValue.replaceAll("[^\\d]", "");
        StringBuilder formatted = new StringBuilder();

        if (digitsOnly.length() <= 16) {
            for (int i = 0; i < digitsOnly.length(); i++) {
                formatted.append(digitsOnly.charAt(i));
                if ((i + 1) % 4 == 0 && (i + 1) != digitsOnly.length()) {
                    formatted.append(" ");
                }
            }
            cardErrorLabel.setText(""); // Clear any previous error message.
        } else {
            cardNumberField.setText(oldValue);
            cardErrorLabel.setText("Please enter a valid card number");
            return; // Exit early as we don't want to process further in this case.
        }

        if (!cardNumberField.getText().equals(formatted.toString())) {
            cardNumberField.setText(formatted.toString());
        }

        cardNumberField.positionCaret(formatted.length());
        validateCardDetails();
    }
    public void formatCcv(String oldValue, String newValue) {
        String digits = newValue.replaceAll("[^\\d]", ""); // Removes non-digit characters
        if (digits.length() > 3) {
            digits = digits.substring(0, 3); // Limit to 3 digits
        }
        ccvTextField.setText(digits);
    }
    public void formatExpDate(String oldValue, String newValue) {
        String cleaned = newValue.replaceAll("[^\\d]", "");
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < cleaned.length(); i++) {
            formatted.append(cleaned.charAt(i));
            if (i == 1 && cleaned.length() > 2) {
                formatted.append('/');
            }
            if (formatted.length() == 5) { // Maximum length of MM/YY is 5 including '/'
                break;
            }
        }

        expDateTextField.setText(formatted.toString());
        validateCardDetails(); // Call to validate the full card details including date
    }

    public void validateCardDetails() {
        String num = ccvTextField.getText();
        String date = expDateTextField.getText().replaceAll("\\s+", "");
        String cardNumber = cardNumberField.getText().replaceAll("\\s+", "");
        String address = addressTextField.getText();
        String name = fullNameTextField.getText();

        if (cardNumber.length() == 16 && validCcv(num) && validExpDate(date) && !address.isEmpty() && !name.isEmpty()) {
            cardErrorLabel.setStyle("-fx-text-fill: green;");
            cardErrorLabel.setText("Details confirmed");
        } else {
            cardErrorLabel.setStyle("-fx-text-fill: red;");
            cardErrorLabel.setText("Please enter valid details");
        }
    }
    public void updateCartDisplay() {
        Platform.runLater(() -> {
//            productGridPane.getChildren().clear(); // Clear the grid first
            int row = 0;
            int column = 0;
            final int maxRowsPerColumn = 7; // Assume max 7 rows per column to avoid subtotal row

            for (SharedModel.Product product : SharedModel.getInstance().getCart().getItems()) {
                if (row >= maxRowsPerColumn) {
                    column++;
                    row = 0;
                }
                Text productName = new Text(product.getTitle());
                Text productPrice = new Text(String.format("$%.2f", Double.parseDouble(product.getPrice().replaceAll("[^\\d.]", ""))));

                VBox productBox = new VBox(productName, productPrice);
                GridPane.setConstraints(productBox, column, row); // Set column and row for VBox
                productGridPane.getChildren().add(productBox); // Add the VBox to the GridPane
                row++; // Move to the next row
            }

            // Update subtotal, taxes, and total
            double subtotal = SharedModel.getInstance().getCart().getSubtotal();
            double tax = SharedModel.getInstance().getCart().getTax();
            double total = subtotal + tax;

            subtotalInCart.setText(String.format("$%.2f", subtotal));
            taxesInCart.setText(String.format("$%.2f", tax));
            totalInCart.setText(String.format("$%.2f", total));

            // Ensure the totals are always in the last row of the first column
            GridPane.setConstraints(subtotalInCart.getParent(), 0, maxRowsPerColumn);
            GridPane.setConstraints(taxesInCart.getParent(), 0, maxRowsPerColumn + 1);
            GridPane.setConstraints(totalInCart.getParent(), 0, maxRowsPerColumn + 2);
        });
    }
    private void processOrder() {
        if (SharedModel.getInstance().getCart().isCartEmpty()) {
            missingItemText.setStyle("-fx-text-fill: red;");
            missingItemText.setText("Cart is Empty");
        } else {
            missingItemText.setStyle("-fx-text-fill: green;");
            missingItemText.setText("Order Has Been Processed");
        }
    }

}
