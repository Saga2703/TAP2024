package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VerificacionGUI extends Stage {

    private VBox mainLayout;
    private Label pass = new Label("Type the password");
    private TextField password =  new TextField("");
    private Label use = new Label("Type the user");
    private TextField user =  new TextField("");;
    private Button submit = new Button("Submit");
    private Scene escena;

    public VerificacionGUI(){
        CrearUI();
        this.setTitle("Taqueria");
        this.setScene(escena);
        this.setMinWidth(720);
        this.setMinHeight(480);
        this.show();
    }

    public void CrearUI(){
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
            a.setTitle("");
            a.setHeaderText("");
            a.setContentText("");
            a.show();
        }
    }

}
