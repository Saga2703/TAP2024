package com.example.tap2024.vistas;

import com.example.tap2024.modelos.OrdenDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;

public class OrdenForm extends Stage {
    private OrdenDAO objOrden;
    String[] arPromts = {"Id_empleado","Fecha","Observaciones"};
    private Scene escena;
    private VBox vbxPrincipal;
    private TextField[] arrCampos= new TextField[3];
    private Button btnGuardar;
    TableView<OrdenDAO> tbvOrden;
    public OrdenForm(TableView<OrdenDAO> tbvOrd, OrdenDAO objOrden) {
        tbvOrden=tbvOrd;
        this.objOrden =(objOrden==null)? new OrdenDAO():objOrden;
        CrearUI();
        this.setTitle("Insertar Producto");
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
        btnGuardar.setOnAction(event -> GuardarOrden());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena=new Scene(vbxPrincipal,300,250);
    }

    private void LlenarForm() {
        arrCampos[0].setText(objOrden.getId_empleado()+"");
        arrCampos[1].setText(objOrden.getFecha()+"");
        arrCampos[2].setText(objOrden.getObservaciones());


    }
    private void GuardarOrden(){
        objOrden.setId_empleado(Integer.parseInt(arrCampos[0].getText()));
        objOrden.setFecha(Date.valueOf(arrCampos[1].getText()));
        objOrden.setObservaciones(arrCampos[2].getText());


        if(objOrden.getId_orden()>0){
            objOrden.ACTUALIZAR();
        }
        else
            objOrden.INSERTAR();
        tbvOrden.setItems(objOrden.CONSULTAR());
        tbvOrden.refresh();

        arrCampos[0].clear();
        arrCampos[1].clear();
        arrCampos[2].clear();

    }
}
