module com.example.tap2024 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tap2024 to javafx.fxml;
    exports com.example.tap2024;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires mariadb.java.client;
    requires java.sql;
    requires kernel;
    requires layout;
    opens com.example.tap2024.modelos;
}