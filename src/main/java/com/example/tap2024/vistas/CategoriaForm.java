package com.example.tap2024.vistas;

import com.example.tap2024.modelos.CategoriaDAO;
import com.example.tap2024.modelos.ProductoDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CategoriaForm extends Stage {
    private CategoriaDAO objCat;
    String[] arPromts = {"Categoria"};
    private Scene escena;
    private VBox vbxPrincipal;
    private TextField[] arrCampos= new TextField[1];
    private Button btnGuardar;
    TableView<CategoriaDAO> tbvCategoria;
    public CategoriaForm(TableView<CategoriaDAO> tbvCat, CategoriaDAO objCat) {
        tbvCategoria=tbvCat;
        this.objCat =(objCat==null)? new CategoriaDAO():objCat;
        CrearUI();
        this.setTitle("Insertar Usuario");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        vbxPrincipal=new VBox();
        vbxPrincipal.setPadding(new Insets(10));
        vbxPrincipal.setSpacing(10);
        vbxPrincipal.setAlignment(Pos.CENTER);
        for (int i=0;i<arrCampos.length;i++){
            arrCampos[i]=new TextField();
            arrCampos[i].setPromptText(arPromts[i]);
            vbxPrincipal.getChildren().add(arrCampos[i]);
        }
        LlenarForm();
        btnGuardar=new Button("Guardar");
        btnGuardar.setOnAction(event -> GuardarCategoria());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena=new Scene(vbxPrincipal,300,250);
    }

    private void LlenarForm() {
        arrCampos[0].setText(objCat.getCategoria());


    }
    private void GuardarCategoria(){
        objCat.setCategoria(arrCampos[0].getText());


        if(objCat.getId_categoria()>0){
            objCat.ACTUALIZAR();
        }
        else
            objCat.INSERTAR();
        tbvCategoria.setItems(objCat.CONSULTAR());
        tbvCategoria.refresh();

        arrCampos[0].clear();
        arrCampos[1].clear();
        arrCampos[2].clear();
        arrCampos[3].clear();
        arrCampos[4].clear();
    }
}
