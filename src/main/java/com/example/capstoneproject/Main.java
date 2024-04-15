package com.example.capstoneproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

import com.google.firebase.auth.*;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class Main extends Application {

        public static Firestore fstore;
        public static FirebaseAuth fauth;
        private final FirebaseContext contxtFirebase = new FirebaseContext();

        @Override
        public void start (Stage primaryStage) throws Exception {


//            FileInputStream serviceAccount =
//                    new FileInputStream("src/main/resources/key.json");
//
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            FirebaseApp.initializeApp(options);

//        fstore = contxtFirebase.firebase();
//        fauth = FirebaseAuth.getInstance();


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
