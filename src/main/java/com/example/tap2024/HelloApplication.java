package com.example.tap2024;


import com.example.tap2024.components.Hilo;


import com.example.tap2024.modelos.Conexion;
import com.example.tap2024.vistas.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private MenuBar mnbPrincipal;
    private Menu menParcial1, menParcial2, menSalir;
    private MenuItem mitCalculadora, mitSalir, mitMemorama,mitCuadroMagico, mitEmpleado,mitPista;
    private MenuItem  mitTaqueria;

    private BorderPane bdpPanel;
    @Override
    public void start(Stage stage) throws IOException {
        CrearMenu();
        bdpPanel = new BorderPane();
        bdpPanel.setTop(mnbPrincipal);
        Scene scene = new Scene(bdpPanel);
        scene.getStylesheets().add(getClass().getResource("/Estilos/taqueria.css").toString());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(false);
/*
        new Hilo("Juno").start();
        new Hilo("Joshua").start();
        new Hilo("Saga").start();
        new Hilo("Alma").start();
        new Hilo("Rafa").start();*/
        Conexion.crearConexion();
    }

    private void CrearMenu() {

        /* Menu primer parcial */
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());

        mitMemorama = new MenuItem("Memorama");
        mitMemorama.setOnAction(event -> new Memorama());

        mitEmpleado = new MenuItem("Empleado Taqueria");
        mitEmpleado.setOnAction(event -> new EmpleadoTaqueria());

        mitCuadroMagico = new MenuItem("Cuadro magico");
        mitCuadroMagico.setOnAction(event -> new CuadroMagico());

        menParcial1 = new Menu("Primer Parcial");
        menParcial1.getItems().addAll(mitCalculadora,mitMemorama,mitCuadroMagico,mitEmpleado);

        /* Menu segundo parcial */
        mitTaqueria = new MenuItem("Taqueria");
        mitTaqueria.setOnAction(event -> new TaqueriaGUI());


        mitPista =new MenuItem("Manejo de Hilos");
        mitPista.setOnAction(event -> new Pista());
        menParcial2 = new Menu("Segundo Parcial");
        menParcial2.getItems().addAll(mitPista,mitTaqueria);

        /* Menu salir */
        mitSalir = new MenuItem("Salir");
        menSalir = new Menu("Salir");
        menSalir.getItems().add(mitSalir);
        mitSalir.setOnAction(event -> System.exit(0));

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(menParcial1,menParcial2,menSalir);

    }

    public static void main(String[] args) {
        launch();
    }
}