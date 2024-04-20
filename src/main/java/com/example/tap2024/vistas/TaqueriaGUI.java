package com.example.tap2024.vistas;

import com.example.tap2024.modelos.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        CrearBAlimentos();
        mnOrden = new VBox(mnTiposAlimentos,mnAlimentos,bAlimentos);
        mnPrincipal.getChildren().add(mnOrden);
    }

    public void CrearMNTiposAlimentos(){
        Image imgTipo;
        ImageView imgTipoView;
        Button bTipo;
        VBox temp = new VBox();
        mnTiposAlimentos = new HBox();
        ObservableList<CategoriaDAO> tipos = categoria.CONSULTAR();
        for(int tipo = 0; tipo < tipos.size(); tipo++){
            imgTipo = ObtenerImg.obtImg(tipos.get(tipo).getCategoria());
            imgTipoView = new ImageView(imgTipo);
            bTipo = new Button(tipos.get(tipo).getCategoria());
            int tempS = tipos.get(tipo).getId_categoria();
            bTipo.setOnAction(event -> CrearMNAlimentos(tempS));
            temp = new VBox(imgTipoView,bTipo);
            mnTiposAlimentos.getChildren().add(temp);
        }
    }

    public void CrearMNAlimentos(int categoria){
        Image imgAlim;
        ImageView imgAlimView;
        Button bAlim;
        VBox temp = new VBox();
        ObservableList<ProductoDAO> alimentos = productos.CONSULTAR();
        mnAlimentos = new GridPane();
        for(int alimento = 0; alimento < alimentos.size(); alimento++){
            imgAlim = ObtenerImg.obtImg(alimentos.get(alimento).getProducto());
            imgAlimView = new ImageView(imgAlim);
            bAlim = new Button(alimentos.get(alimento).getProducto());
            temp = new VBox(imgAlimView, bAlim);
            mnAlimentos.add(temp,alimento%5,alimento/5);
        }
    }

    public void CrearBAlimentos(){

    }

    public void CrearMNMesas(){
        mnMesas = new VBox(mesas, bMesas, menuPrivado);
        mnPrincipal.getChildren().add(mnMesas);
    }


}
