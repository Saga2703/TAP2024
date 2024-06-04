package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VerificacionGUI extends Stage {

    private static boolean existAlready = false;
    private VBox mainLayout;
    private Label pass = new Label("Type the password");
    private TextField password =  new TextField("");
    private Label use = new Label("Type the user");
    private TextField user =  new TextField("");;
    private Button submit = new Button("Submit");
    private Scene escena;

    public VerificacionGUI(){
        if (existAlready == false) {
            existAlready = true;
            CrearUI();
            this.setTitle("Taqueria");
            this.setScene(escena);
            this.setMinWidth(720);
            this.setMinHeight(480);
            this.show();
            this.setOnCloseRequest(event -> existAlready = false);
        }
    }

    public void CrearUI(){
        user.setStyle("-fx-background-color: #4e4e4e;-fx-text-fill: #AFB1B3");
        password.setStyle("-fx-background-color: #4e4e4e;-fx-text-fill: #AFB1B3");
        submit.setOnAction(event -> goToAdmin());
        mainLayout = new VBox(use,user,pass,password,submit);
        escena = new Scene(mainLayout);
        escena.getStylesheets().add(getClass().getResource("/Estilos/taqueria.css").toString());
    }

    public void goToAdmin(){
        if((user.getText().equals("root")) && (password.getText().equals("1234"))) {
            new AdminTaqueriaGUI();
        }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            DialogPane dialogPane = a.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/Estilos/taqueria.css").toString());
            dialogPane.getStyleClass().add("alert");
            a.setTitle("");
            a.setHeaderText("");
            a.setContentText("Please type the correct user and password");
            a.show();
        }
    }

}
