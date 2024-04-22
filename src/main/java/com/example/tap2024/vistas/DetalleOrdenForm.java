package com.example.tap2024.vistas;

import com.example.tap2024.modelos.Detalle_OrdenDAO;
import com.example.tap2024.modelos.ProductoDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetalleOrdenForm extends Stage {
    private Detalle_OrdenDAO objDetalle;
    String[] arPromts = {"Id_Orden","Id_Producto","Cantidad","Precio"};
    private Scene escena;
    private VBox vbxPrincipal;
    private TextField[] arrCampos= new TextField[4];
    private Button btnGuardar;
    TableView<Detalle_OrdenDAO> tbvDetalle;
    public DetalleOrdenForm(TableView<Detalle_OrdenDAO> tbvDet, Detalle_OrdenDAO objDet) {
        tbvDetalle=tbvDet;
        this.objDetalle =(objDetalle==null)? new Detalle_OrdenDAO():objDetalle;
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
        btnGuardar.setOnAction(event -> GuardarProducto());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena=new Scene(vbxPrincipal,300,250);
    }

    private void LlenarForm() {
        arrCampos[0].setText(objDetalle.getId_orden()+"");
        arrCampos[1].setText(objDetalle.getId_producto()+"");
        arrCampos[2].setText(objDetalle.getCantidad()+"");
        arrCampos[3].setText(objDetalle.getPrecio()+"");

    }
    private void GuardarProducto(){
        objDetalle.setId_orden(Integer.parseInt(arrCampos[0].getText()));
        objDetalle.setId_producto(Integer.parseInt((arrCampos[1].getText())));
        objDetalle.setCantidad(Integer.parseInt((arrCampos[2].getText())));
        objDetalle.setPrecio(Integer.parseInt(arrCampos[3].getText()));

        if(objDetalle.getId_producto()>0){
            objDetalle.ACTUALIZAR();
        }
        else
            objDetalle.INSERTAR();
        tbvDetalle.setItems(objDetalle.CONSULTAR());
        tbvDetalle.refresh();

        arrCampos[0].clear();
        arrCampos[1].clear();
        arrCampos[2].clear();
        arrCampos[3].clear();
        arrCampos[4].clear();
    }
}
