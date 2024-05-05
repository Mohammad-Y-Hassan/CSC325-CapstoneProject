package com.example.capstoneproject;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseContext {

    private static FirebaseApp firebaseApp;
    private static Firestore firestore;
    private static FirebaseAuth firebaseAuth;

//    static {
//        initializeFirebase();
//    }

    public static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/key.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) { // Check for existing app instances
                firebaseApp = FirebaseApp.initializeApp(options);
                System.out.println("Firebase App initialized.");
            } else {
                firebaseApp = FirebaseApp.getInstance();
            }

            // Ensure FirebaseAuth and Firestore instances are initialized
            firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
            firestore = FirestoreClient.getFirestore(firebaseApp);

            if (firebaseAuth == null) {
                System.out.println("Firebase Auth is null after initialization.");
            } else {
                System.out.println("Firebase Auth initialized successfully.");
            }

            if (firestore == null) {
                System.out.println("Firestore is null after initialization.");
            } else {
                System.out.println("Firestore initialized successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Failed to initialize Firebase: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static Firestore getFirestore() {
        if (firestore == null) System.out.println("Firestore was requested but is not initialized.");
        return firestore;
    }

    public static FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth == null) System.out.println("FirebaseAuth was requested but is not initialized.");
        return firebaseAuth;
    }
}