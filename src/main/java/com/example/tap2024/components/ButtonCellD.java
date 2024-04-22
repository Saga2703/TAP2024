package com.example.tap2024.components;

import com.example.tap2024.modelos.Detalle_OrdenDAO;
import com.example.tap2024.modelos.EmpleadosDAO;
import com.example.tap2024.vistas.DetalleOrdenForm;
import com.example.tap2024.vistas.EmpleadosForm;
import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCellD extends TableCell<Detalle_OrdenDAO,String> {
    Detalle_OrdenDAO objDetO;
    Button btnCelda;
    int opc;
    public ButtonCellD(int opc){
        this.opc =opc;
        String txtButton =(opc==1)?"Editar":"Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<Detalle_OrdenDAO> tbvDet= ButtonCellD.this.getTableView();
        objDetO = tbvDet.getItems().get(ButtonCellD.this.getIndex());
        if(opc==1){
            //Editar
            new DetalleOrdenForm(tbvDet,objDetO);
        }else{
            //Eliminar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar el Empleado: "+objDetO.getId_orden()+"?");
            Optional<ButtonType> result =alert.showAndWait();
            if (result.get()==ButtonType.OK){
                objDetO.ELIMINAR();
                tbvDet.setItems(objDetO.CONSULTAR());
                tbvDet.refresh();
            }
        }
    }

    @Override
    protected void updateItem(String item,boolean empty){
        super.updateItem(item,empty);
        if (!empty)
            this.setGraphic(btnCelda);
    }
}
