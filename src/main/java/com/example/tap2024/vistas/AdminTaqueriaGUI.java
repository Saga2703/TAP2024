package com.example.tap2024.vistas;

import com.example.tap2024.PDFTools;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class AdminTaqueriaGUI extends Stage {


    private Scene escena;
    private HBox userMN;
    private HBox pwdMN;
    private Button validar;
    private boolean validado;

    AdminTaqueriaGUI(){
        CrearUI();
        this.setTitle("Administracion");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI(){

    }

    public void CrearMenuModificaciones(){

    }

}
