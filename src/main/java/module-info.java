module com.example.timeplanning {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.timeplanning to javafx.fxml;
    exports com.example.timeplanning;
}