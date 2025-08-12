module org.example.firstsemfptolayerd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires jakarta.mail;


    opens org.example.firstsemfptolayerd.controller to javafx.fxml;
    exports org.example.firstsemfptolayerd;
    opens org.example.firstsemfptolayerd.view.tdm to javafx.base;
    opens org.example.firstsemfptolayerd.model to javafx.base;
}