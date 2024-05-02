package com.example.capstoneproject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseContext {

    private static Firestore firestore;

    public static synchronized void initializeFirebase() {
        if (firestore != null) {
            return;
        }

        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/key.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            firestore = FirestoreClient.getFirestore(); // Set Firestore instance
        } catch (IOException e) {
            System.err.println("Failed to initialize Firebase: " + e.getMessage());
            e.printStackTrace();
            System.exit(1); // Exit to avoid inconsistency
        }
    }

    public static Firestore getFirestore() {
        if (firestore == null) {
            initializeFirebase(); // Initialize if not already
        }
        return firestore;
    }
}
