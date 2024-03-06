package com.example.tap2024.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class memorama extends Stage {
    private Scene escena;
    private VBox vContenedor1, vContenedor2;
    private HBox hContenedor1,hContenedor2, hContenedor3,hContenedor4;
    private Button boton=new Button("Empezar");
    private TextField txtPantalla;
    private GridPane gdpImagenes;
    private Label lblPares, lbl1,lbl2,lbl3,lbl4,lblTimer;

    public memorama(){
        CrearUI();
        this.setTitle("Memorama ");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI() {
        lblPares = new Label("No pares");
        txtPantalla = new TextField();
        lblTimer =new Label("00.00");
        gdpImagenes = new GridPane();
        RevolverCartas();
        hContenedor1 = new HBox(lblPares,txtPantalla,boton,lblTimer);
        hContenedor1.setSpacing(10);
        lbl1 = new Label("Jugador 1");
        lbl2 = new Label("Jugador 2");
        lbl3 = new Label("...");
        lbl4 = new Label("...");
        hContenedor3=new HBox(lbl1,lbl3);
        hContenedor3.setSpacing(20);
        hContenedor4=new HBox(lbl2,lbl4);
        hContenedor4.setSpacing(20);
        vContenedor2 = new VBox(hContenedor3,hContenedor4);
        hContenedor2 = new HBox(gdpImagenes,vContenedor2);
        hContenedor2.setSpacing(50);
        vContenedor1 = new VBox(hContenedor1,hContenedor2);
        vContenedor1.setSpacing(10);
        escena = new Scene(vContenedor1, 500,500);
        /*escena.getStylesheets()
                .add(getClass().getResource("/estilos/memorama.css").toString());*/
    }

    private void RevolverCartas(){
        String[] arImagenes={"link oot.jpg","sheik.jpg","zelda.jpg","trifuerza.jpg"};
        Button[][] arBtncartas= new Button[2][4];

        ImageView imvCarta;
        int posx=0;
        int posy=0;
        int cont=0;
        for (int i=0;i<arImagenes.length;){
            posx= (int) (Math.random()*2);
            posy= (int) (Math.random()*4);
            if (arBtncartas[posx][posy]==null){
                arBtncartas[posx][posy]= new Button();
                imvCarta = new ImageView(
                        getClass().getResource("/imagenes/"+arImagenes[i]).toString()
                );
                imvCarta.setFitHeight(150);
                imvCarta.setFitWidth(100);
                arBtncartas[posx][posy].setGraphic(imvCarta);
                arBtncartas[posx][posy].setPrefSize(100,150);
                gdpImagenes.add(arBtncartas[posx][posy],posy,posx);
                cont++;
                if (cont==2) {
                    i++;
                    cont=0;
                }
            }
        }
    }
}
