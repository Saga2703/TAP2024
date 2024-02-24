package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

    private Scene escena;
    private VBox vContenedor;
    private GridPane gdpTeclado;
    private TextField txtPantalla;
    private Button[][] arBotones = new Button[5][4];
    private char[] arEtiquetas = {' ',' ','D','C','7','8','9','/','4','5','6','*','1','2','3','-','0','.','=','+'};
    private String operador;
    private double primerNumero;
    private boolean operacionRealizada = false;


    public Calculadora(){
        CrearUI();
        this.setTitle("Mi primer Calculadora :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        txtPantalla = new TextField("");
        txtPantalla.setEditable(false);
        txtPantalla.setPrefColumnCount(10);
        gdpTeclado = new GridPane();
        CrearTeclado();
        vContenedor = new VBox(txtPantalla,gdpTeclado);
        vContenedor.setSpacing(5);
        escena = new Scene(vContenedor, 200,200);
        escena.getStylesheets()
                .add(getClass().getResource("/estilos/calculadora.css").toString());
    }

    private void CrearTeclado() {
        int pos = 0;
        char simbolo;
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 4; j++) {
                arBotones[i][j] = new Button(arEtiquetas[pos]+"");
                arBotones[i][j].setPrefSize(50,50);
                int finalPos = pos;
                arBotones[i][j].setOnAction(event -> setValue(arEtiquetas[finalPos]));
                gdpTeclado.add(arBotones[i][j],j,i);

                if(arEtiquetas[pos]=='+'|| arEtiquetas[pos]=='-'||arEtiquetas[pos]=='*'||arEtiquetas[pos]=='/')
                    arBotones[i][j].setId("color-operador");
                pos++;
            }
        }
    }

    private void setValue(char simbolo) {
        if (simbolo == ' ') {
            return;
        }
        if (Character.isDigit(simbolo)) {
            txtPantalla.appendText(simbolo + "");
            operacionRealizada = false;
        } else if (simbolo == '.' && !txtPantalla.getText().contains(".")) {
            txtPantalla.appendText(simbolo + "");
            operacionRealizada = false;
        } else if (simbolo == 'C') {
            txtPantalla.clear();
            operador = null;
            primerNumero = 0;
            operacionRealizada = false;
        } else if (simbolo == 'D') {
            String textoPantalla = txtPantalla.getText();
            if (!textoPantalla.isEmpty()) {
                txtPantalla.setText(textoPantalla.substring(0, textoPantalla.length() - 1));
            }
        } else if (simbolo == '=') {
            if (operador != null && !operacionRealizada) {
                String textoPantalla = txtPantalla.getText();
                if (!textoPantalla.isEmpty()) {
                    double segundoNumero = Double.parseDouble(textoPantalla);
                    double resultado = calcular(primerNumero, segundoNumero, operador);
                    txtPantalla.setText(String.valueOf(resultado));
                    operador = null;
                    operacionRealizada = true;
                }
            }
        } else {
            if (!operacionRealizada) {
                if (!txtPantalla.getText().isEmpty()) {
                    operador = String.valueOf(simbolo);
                    primerNumero = Double.parseDouble(txtPantalla.getText());
                    txtPantalla.clear();
                    operacionRealizada = true;
                }
            }
        }
    }


    private double calcular(double num1, double num2, String operador) {
        switch (operador) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }

}
