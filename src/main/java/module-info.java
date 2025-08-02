module org.example.firstsemfptolayerd {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.firstsemfptolayerd to javafx.fxml;
    exports org.example.firstsemfptolayerd;
}