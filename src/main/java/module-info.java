module com.example.tm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tm to javafx.fxml;
    exports com.example.tm;
}