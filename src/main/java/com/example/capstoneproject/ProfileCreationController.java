package com.example.capstoneproject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
public class ProfileCreationController extends SceneLoader {
    @FXML
    private ImageView uploadProfilePictureIcon;

    @FXML
    private TextField nameTextField;

    public void initialize() {
        nameTextField.setText(SharedModel.getInstance().getFirstName());
    }

    public void uploadPost(MouseEvent event) throws IOException {
        toUploadPostView(event);
    }
    public void toProfile(MouseEvent event) throws IOException{
        toProfileView(event);
    }
    public void returntoHome(MouseEvent event) throws IOException {
        toHomeView(event);
    }
    public void toCart(MouseEvent event) throws IOException {
        toCartView(event);
    }

    public void uploadPFP(MouseEvent event)throws IOException {
        FileChooser fileChooser = new FileChooser();
        //sets extension filters
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        //open file dialog
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            uploadProfilePictureIcon.setImage(image);
        }
    }
}
