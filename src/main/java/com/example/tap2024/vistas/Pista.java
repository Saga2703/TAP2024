package com.example.tap2024.vistas;

import com.example.tap2024.components.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Pista extends Stage {
    private ProgressBar[] pgbCorredores = new ProgressBar[5];
    private Label[] lblCorredores =new Label[5];
    private GridPane gdpPista;
    private Scene escena;
    private String[] strCorredores= {"Juno","Rafa","Joshua","Alma","Saga"};
    private Hilo[] thrCorredores =new Hilo[5];
    public Pista(){
        CrearUI();
        this.setTitle("Pista de atletismo");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        gdpPista = new GridPane();
        for (int i = 0; i < strCorredores.length; i++) {
            lblCorredores[i]=new Label(strCorredores[i]);
            pgbCorredores[i]=new ProgressBar(0);
            gdpPista.add(lblCorredores[i],0,i);
            gdpPista.add(pgbCorredores[i],1,i);
            thrCorredores[i]=new Hilo(strCorredores[i]);
            thrCorredores[i].setPgbCarril(pgbCorredores[i]);
            thrCorredores[i].start();
        }
        escena=new Scene(gdpPista,200,200);
    }
}
