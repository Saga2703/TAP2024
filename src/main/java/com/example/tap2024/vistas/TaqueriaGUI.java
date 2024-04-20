package com.example.tap2024.vistas;

import com.example.tap2024.modelos.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaqueriaGUI extends Stage {
    
    private EmpleadosDAO empleados;
    private ProductoDAO productos;
    private OrdenDAO ordenes;
    private Detalle_OrdenDAO detalleOrden;
    private CategoriaDAO categoria;

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
        CrearMNMesas();
        escena = new Scene(mnPrincipal);
    }

    public void CrearMNOrden(){
        CrearMNTiposAlimentos();
        CrearMNAlimentos();
        CrearBAlimentos();
        mnOrden = new VBox(mnTiposAlimentos,mnAlimentos,bAlimentos);
        mnPrincipal.getChildren().add(mnOrden);
    }

    public void CrearMNTiposAlimentos(){
        Image imgTipo;
        ImageView imgTipoView;
        Label lbTipo;
        VBox temp = new VBox();
        mnTiposAlimentos = new HBox();
        ObservableList<CategoriaDAO> tipos = categoria.CONSULTAR();
        for(int tipo = 0; tipo < tipos.size(); tipo++){
            imgTipo = ObtenerImg.obtImg(tipos.get(tipo).getCategoria());
            imgTipoView = new ImageView(imgTipo);
            lbTipo = new Label(tipos.get(tipo).getCategoria());
            temp = new VBox(imgTipoView,lbTipo);
            mnTiposAlimentos.getChildren().add(temp);
        }
    }

    public void CrearMNAlimentos(){
        Image imgAlim;
        ImageView imgAlimView;
        Label lbAlim;
        mnAlimentos = new GridPane();
    }

    public void CrearBAlimentos(){

    }

    public void CrearMNMesas(){
        mnMesas = new VBox(mesas, bMesas, menuPrivado);
        mnPrincipal.getChildren().add(mnMesas);
    }


}
