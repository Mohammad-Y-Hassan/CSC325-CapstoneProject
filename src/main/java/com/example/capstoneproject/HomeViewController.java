package com.example.capstoneproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeViewController extends SceneLoader{

    @FXML
    private TextField SearchField;

    @FXML
    private ImageView uploadPostButton;

    @FXML
    private GridPane productGridPane;

    @FXML
    private Label ItemNumberLabel;


    private int itemCount = 0;

    @FXML
    public void initialize() {
        updateItemCountDisplay(); // Update the display when the view is first loaded
        setupSearchBar(); // Set up the search bar
    }

    private void setupSearchBar() {
        SearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchProducts(newValue.toLowerCase()); // Convert search text to lower case for case-insensitive comparison
        });
    }

    public void uploadPost(MouseEvent event) throws IOException {
        toUploadPostView(event);
    }
    public void toProfile(MouseEvent event) throws IOException{
        toProfileView(event);
    }
    public void toCart(MouseEvent event) throws IOException {
        toCartView(event);
    }

    public void addItemToCart(ActionEvent event) {
        // This method is called when the "Add to Cart" button is clicked
        itemCount++; // Increment the item count
        updateItemCountDisplay(); // Update the label that displays the item count
    }
    public void updateItemCountDisplay() {
        ItemNumberLabel.setText(String.valueOf(itemCount)); // Update the text of the item count label
    }

    private void searchProducts(String searchText) {
        for (Node node : productGridPane.getChildren()) {
            // Check if the node is a VBox
            if (node instanceof VBox) {
                VBox vbox = (VBox) node;
                boolean matchFound = false;

                // Loop through VBox children to find Labels
                for (Node child : vbox.getChildren()) {
                    if (child instanceof Label) {
                        Label label = (Label) child;
                        // Check if the label text contains the search text
                        if (label.getText().toLowerCase().contains(searchText)) {
                            matchFound = true; // A matching label was found within this VBox
                            break; // No need to check other labels in this VBox
                        }
                    }
                }
                vbox.setVisible(matchFound); // Set visibility based on whether a match was found
            }
        }
    }
}
