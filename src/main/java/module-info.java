module com.example.capstoneproject {
    requires javafx.controls;
    requires javafx.fxml;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires org.kordamp.bootstrapfx.core;
    requires firebase.admin;
    requires com.google.auth;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.api.apicommon;
    requires com.fasterxml.jackson.databind;

    opens com.example.capstoneproject to javafx.fxml, com.fasterxml.jackson.databind;

    exports com.example.capstoneproject;
}