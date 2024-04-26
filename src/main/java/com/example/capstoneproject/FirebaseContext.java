package com.example.capstoneproject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;

public class FirebaseContext {

    private static Firestore firestore;

    public static synchronized void initializeFirebase() {
        if (firestore != null) {
            return; // Firestore is already initialized, no need to reinitialize
        }

        try {
            // Load the service account key JSON file from a specified path
            FileInputStream serviceAccount =
                    new FileInputStream("src/resources/key.json"); // Change this to your actual service account file
            if (serviceAccount == null) {
                System.err.println("Service account stream is null");
                return;
            }

            // Use GoogleCredentials to generate the credentials for Firebase
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            // Build the FirebaseOptions using the credentials
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();

            // Check if any FirebaseApp instances already exist, if not initialize a new one
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // Obtain a Firestore instance from the initialized app
            firestore = FirestoreClient.getFirestore();

        } catch (Exception e) {
            System.err.println("Failed to initialize Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Firestore getFirestore() {
        if (firestore == null) {
            initializeFirebase(); // Initialize only if it has not been initialized yet
        }
        return firestore;
    }
}

