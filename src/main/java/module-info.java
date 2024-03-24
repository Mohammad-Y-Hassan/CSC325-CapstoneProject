module com.example.capstoneproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires firebase.admin;
    requires com.google.auth;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.api.apicommon;

    opens com.example.capstoneproject to javafx.fxml;
    exports com.example.capstoneproject;
}