package com.example.capstoneproject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

import com.google.firebase.auth.*;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import java.io.FileInputStream;
import com.google.firebase.FirebaseOptions;
// import com.example.capstoneproject.FirestoreContext;
public class Main extends Application {

    public static Firestore fstore;
    public static FirebaseAuth fauth;
 //   private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage primaryStage) throws Exception {
//        fstore = contxtFirebase.firebase();
//        fauth = FirebaseAuth.getInstance();
        // Initialize Firebase
//        FileInputStream serviceAccount =
//                new FileInputStream(String.valueOf(getClass().getResource("key.json")));

//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();

//        FirebaseApp.initializeApp(options);

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

    public static void main(String[] args) {
        launch();
    }
}
