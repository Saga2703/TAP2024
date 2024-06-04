package com.example.tap2024.vistas;

import com.example.tap2024.PDFTools;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class AdminTaqueriaGUI extends Stage {

    private static boolean existAlready = false;
    private Scene escena;
    private Button orden = new Button("Mod. orden");
    private Button detOrden = new Button("Mod. det. orden");
    private Button producto = new Button("Mod. producto");
    private Button empleado = new Button("Mod. empleado");
    private Button categoria = new Button("Mod. categoria");
    private VBox modificaciones = new VBox(orden, producto, empleado, categoria);
    private HBox userMN;
    private HBox pwdMN;
    private Button validar;
    private boolean validado;

    AdminTaqueriaGUI(){
        if(existAlready == false) {
            existAlready = true;
            CrearUI();
            escena.getStylesheets().add(getClass().getResource("/Estilos/taqueria.css").toString());
            this.setMinHeight(200);
            this.setMinWidth(200);
            this.setTitle("Administracion");
            this.setScene(escena);
            this.show();
            this.setOnCloseRequest(event -> existAlready = false);
        }
    }

    public void CrearUI(){
        escena = new Scene(modificaciones);
        CrearMenuModificaciones();
    }

    public void CrearMenuModificaciones(){
        orden.setOnAction(event -> new OrdenTaqueria());
        detOrden.setOnAction(event -> new Detalle_OrdenTaqueria());
        producto.setOnAction(event -> new ProductoTaqueria());
        empleado.setOnAction(event -> new EmpleadoTaqueria());
        categoria.setOnAction(event -> new CategoriaTaqueria());
    }

}
