package com.example.capstoneproject;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UploadPostController extends SceneLoader{
    @FXML
    private ImageView uploadPostPic;
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
}


