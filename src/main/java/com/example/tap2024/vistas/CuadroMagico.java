package com.example.tap2024.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CuadroMagico extends Stage {
    private Scene escena;
    private VBox vContenedor1;
    private HBox hContenedor,hContenedor2;
    private BorderPane bdpAllM;
    private Label lblT, lbl1;
    private TextField txtNum;
    private Button btnNum,btnRegresar;

    public CuadroMagico() {
        CrearUI();
        this.setTitle("Cuadro Magico");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        lblT = new Label("Cuadro Magico");
        lbl1 = new Label("Numero de orden");
        txtNum = new TextField();
        btnNum = new Button("Calcular");

        hContenedor = new HBox(lbl1, txtNum, btnNum);
        hContenedor.setSpacing(10);
        vContenedor1 = new VBox(lblT, hContenedor);
        vContenedor1.setAlignment(Pos.TOP_CENTER);
        bdpAllM = new BorderPane(vContenedor1);

        btnNum.setOnAction(event -> calcularCuadroMagico());

        escena = new Scene(bdpAllM, 400, 200);
        escena.getStylesheets()
                .add(getClass().getResource("/estilos/cuadroMagico.css").toString());
    }

    private void calcularCuadroMagico() {
        try {
            int orden = Integer.parseInt(txtNum.getText());
            if (orden % 2 == 0) {
                // Solo funciona para órdenes impares
                return;
            }

            File archivo = new File("cuadro_magico.dat");
            RandomAccessFile randomAccessFile = new RandomAccessFile(archivo, "rw");

            // Lógica para calcular el cuadro mágico sin arreglos
            int[][] cuadroMagico = new int[orden][orden];
            int fila = 0;
            int columna = orden / 2;

            for (int i = 1; i <= orden * orden; i++) {
                cuadroMagico[fila][columna] = i;
                fila = (fila - 1 + orden) % orden;
                columna = (columna + 1) % orden;
            }

            // Escribir el cuadro mágico en el archivo de acceso aleatorio
            for (int i = 0; i < orden; i++) {
                for (int j = 0; j < orden; j++) {
                    randomAccessFile.writeInt(cuadroMagico[i][j]);
                }
            }

            // Cerrar el archivo
            randomAccessFile.close();

            // Mostrar cuadro mágico en una nueva ventana usando GridPane
            mostrarCuadroMagico(orden);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void mostrarCuadroMagico(int orden) {
        Stage nuevaVentana = new Stage();
        GridPane gdpCuadro = new GridPane();

        // Leer el cuadro mágico desde el archivo
        try {
            File archivo = new File("cuadro_magico.dat");
            RandomAccessFile randomAccessFile = new RandomAccessFile(archivo, "r");

            for (int i = 0; i < orden; i++) {
                for (int j = 0; j < orden; j++) {
                    int numero = randomAccessFile.readInt();
                    Label label = new Label(String.valueOf(numero));
                    gdpCuadro.add(label, j, i);
                }
            }

            randomAccessFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        btnRegresar =new Button("Regresar");
        hContenedor2 = new HBox(gdpCuadro,btnRegresar);
        hContenedor2.setSpacing(15);
        hContenedor2.setAlignment(Pos.CENTER);
        Scene nuevaEscena = new Scene(hContenedor2, 400, 400);
        nuevaEscena.getStylesheets()
                .add(getClass().getResource("/estilos/cuadroMagico.css").toString());
        nuevaVentana.setScene(nuevaEscena);

        nuevaVentana.setTitle("Cuadro Mágico Resultante");
        nuevaVentana.show();
    }
}


