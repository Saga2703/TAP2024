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

import java.util.HashMap;

public class TaqueriaGUI extends Stage {


    private int mesaActual;//Variable para indicar la mesa en la que se lleva a cabo la orden

    //Modelos usados para interactuar con la base de datos
    private EmpleadosDAO empleados = new EmpleadosDAO();
    private ProductoDAO productos = new ProductoDAO();
    private OrdenDAO ordenes = new OrdenDAO();
    private Detalle_OrdenDAO detalleOrden = new Detalle_OrdenDAO();
    private CategoriaDAO categoria = new CategoriaDAO();

    //Elementos graficos de la aplicacion
    private Scene escena;
    private GridPane mesas;//Grid donde se veran las mesas
    private HBox menuPrivado;//Menu para acceder a las opciones del admin (Base de datos)
    private VBox mnMesas;
    private HBox mnTiposAlimentos;//Meni para seleccionar el tipo de alimento
    private GridPane mnAlimentos;//Menu para seleccionar el alimento
    private HBox bAlimentos;//Menu para agregar o quitar alimentos
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

    //Menu para las ordenes
    public void CrearMNOrden(){
        Label tOrden = new Label("Orden");
        CrearMNTiposAlimentos();
        CrearBAlimentos();
        mnOrden = new VBox(tOrden,mnTiposAlimentos,mnAlimentos,bAlimentos);
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
        HashMap<String, Integer> orden = new HashMap<>();
        bAlimentos = new HBox(new Label("hola"));
    }

    public void CrearMNMesas(){
        Label tMesas = new Label("Mesas");
        CrearMesas();
        CrearMenuPrivado();
        mnMesas = new VBox(tMesas, mesas, menuPrivado);
        mnPrincipal.getChildren().add(mnMesas);
    }

    public void CrearMesas(){
        Button[] bMesa = new Button[12];
        mesas = new GridPane();
        for(int mesa = 0; mesa < 12; mesa++){
            bMesa[mesa] = new Button((String.valueOf(mesa + 1)));
            int finalMesa = mesa;
            bMesa[mesa].setOnAction(event -> {this.mesaActual = finalMesa;});
            mesas.add(bMesa[mesa],mesa%3,mesa/3);
        }
    }


    public void CrearMenuPrivado(){
        Button mnPriv = new Button("Admin Tools");
        mnPriv.setOnAction(event -> new AdminTaqueriaGUI());
        menuPrivado = new HBox(mnPriv);
    }
}
