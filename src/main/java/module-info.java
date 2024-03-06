module com.example.tap2024 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tap2024 to javafx.fxml;
    exports com.example.tap2024;
    requires mysql.connector.j;
    requires mariadb.java.client;
    requires java.sql;
    opens com.example.tap2024.modelos;
}