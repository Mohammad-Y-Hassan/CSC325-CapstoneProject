package com.example.capstoneproject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;

import javafx.application.Application;

import java.io.FileInputStream;

public class Main extends Application {

//        public static Firestore fstore;
//        public static FirebaseAuth fauth;
//        private final FirebaseContext contxtFirebase = new FirebaseContext();

        @Override
        public void start (Stage primaryStage) throws Exception {


//            FirebaseContext.initializeFirebase();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/capstoneproject/CreateAccount.fxml"));
            // Set the root element type programmatically
            BorderPane root = new BorderPane();
            loader.setRoot(root);
            // Now load the FXML
            loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        public static void Main(String[] args){
            launch();
        }
}
