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
    private HBox hContenedor, hContenedor2;
    private BorderPane bdpAllM;
    private Label lblT, lbl1;
    private TextField txtNum;
    private Button btnNum, btnRegresar;

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

            CuadroMagicoLogica cuadroMagicoLogica = new CuadroMagicoLogica(orden);
            cuadroMagicoLogica.generateMagicSquare();

            // Mostrar cuadro mágico en una nueva ventana usando GridPane
            mostrarCuadroMagico(orden, cuadroMagicoLogica);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void mostrarCuadroMagico(int orden, CuadroMagicoLogica cuadroMagicoLogica) {
        Stage nuevaVentana = new Stage();
        GridPane gdpCuadro = new GridPane();

        cuadroMagicoLogica.showMagicSquareGUI(gdpCuadro);
        btnRegresar = new Button("Regresar");
        btnRegresar.setOnAction(event -> nuevaVentana.close());

        hContenedor2 = new HBox(gdpCuadro, btnRegresar);
        hContenedor2.setSpacing(15);
        hContenedor2.setAlignment(Pos.CENTER);
        Scene nuevaEscena = new Scene(hContenedor2, 500, 400);
        nuevaEscena.getStylesheets()
                .add(getClass().getResource("/estilos/cuadroMagico.css").toString());
        nuevaVentana.setScene(nuevaEscena);

        nuevaVentana.setTitle("Cuadro Mágico Resultante");
        nuevaVentana.show();
    }


}

 class CuadroMagicoLogica {

    RandomAccessFile cuadroMagico;
    int n;

    public CuadroMagicoLogica(int n){
        try {
            cuadroMagico = new RandomAccessFile("cuadro.dat", "rw");
            for(int i = 0; i < Math.pow(n, 2); i++){
                cuadroMagico.seek(i * 4);
                cuadroMagico.writeInt(-1);
            }
            this.n = n;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void generateMagicSquare(){
        boolean normalFlag = false;
        int i = 0;
        int j = n / 2;
        man(i, j, 1);
        for(int k = 2; k <= Math.pow(n, 2); k++){
            normalFlag = false;
            if(i == 0 && j == (n - 1)){
                i++;
            }else if(i == 0){
                i = n - 1;
                j++;
            }else if (j == (n - 1)){
                i--;
                j = 0;
            }else{
                i--;
                j++;
                normalFlag = true;
            }
            if(isNotEmpty(i, j)){
                if(normalFlag){
                    i = i + 2;
                    j--;
                }else {
                    i++;
                }
            }
            man(i, j, k);
        }
    }

    public boolean isNotEmpty(int i, int j){
        boolean result = true;
        try {
            cuadroMagico.seek(0);
            cuadroMagico.seek(((i * n) + j) * 4);
            if(cuadroMagico.readInt() < 0) result = false;
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public void man(int i, int j, int data){
        try {
            cuadroMagico.seek(0);
            cuadroMagico.seek(((i * n) + j) * 4);
            cuadroMagico.writeInt(data);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showMagicSquareGUI(GridPane square){
        try {
            for (int i = 0; i < Math.pow(n, 2); i++) {
                cuadroMagico.seek(i * 4);
                square.add(new Button(String.format("%5s", String.valueOf(cuadroMagico.readInt()))), i % n, i / n);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}



