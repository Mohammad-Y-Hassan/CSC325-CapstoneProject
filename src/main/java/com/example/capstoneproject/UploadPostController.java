package com.example.capstoneproject;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UploadPostController implements Initializable {
    @FXML
    private ImageView uploadPostPic;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    private final String[] categoryChoices = {"Electronics", "Clothing","Decor","Other" };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryChoiceBox.getItems().addAll(categoryChoices);
    }
    //returns user to home screen by clicking MarketMate Logo
    public void returntoHome(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/HomeView.fxml"));

        // Load the FXML content
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
    public void toProfile(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/profileCreation.fxml"));

        // Load the FXML content
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
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
    public void toCart(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/cartView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}


