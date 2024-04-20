package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaqueriaGUI extends Stage {

    private Scene escena;
    private GridPane mesas;
    private HBox bMesas;
    private HBox menuPrivado;
    private VBox mnMesas;
    private HBox mnTiposAlimentos;
    private GridPane mnAlimentos;
    private HBox bAlimentos;
    private VBox mnOrden;
    private HBox mnPrincipal;

    public TaqueriaGUI(){
        CrearUI();
        this.setTitle("Taqueria");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI(){
        mnPrincipal = new HBox();
        CrearMNOrden();
        CrearMNMesas();mnPrincipal.getChildren().add(mnOrden);
        escena = new Scene(mnPrincipal);
    }

    public void CrearMNOrden(){
        mnOrden = new VBox(mnTiposAlimentos,mnAlimentos,bAlimentos);
        mnPrincipal.getChildren().add(mnOrden);
    }

    public void CrearMNMesas(){
        mnMesas = new VBox(mesas, bMesas, menuPrivado);
        mnPrincipal.getChildren().add(mnOrden);
    }


}
