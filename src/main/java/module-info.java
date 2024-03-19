module com.example.capstoneproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.capstoneproject to javafx.fxml;
    exports com.example.capstoneproject;
}