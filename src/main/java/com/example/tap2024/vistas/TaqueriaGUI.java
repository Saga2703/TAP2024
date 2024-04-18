package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaqueriaGUI extends Stage {

    private Scene escena;
    private MenuBar mnbPrincipal;
    private GridPane mesas;
    private HBox mnTiposAlimentos;
    private GridPane mnAlimentos;
    private VBox mnOrden;
    private HBox mnPrincipal;

    public TaqueriaGUI(){
        CrearUI();
        this.setTitle("Taqueria");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI(){

    }

}
