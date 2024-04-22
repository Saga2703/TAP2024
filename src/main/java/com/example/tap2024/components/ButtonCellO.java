package com.example.tap2024.components;

import com.example.tap2024.modelos.EmpleadosDAO;
import com.example.tap2024.modelos.OrdenDAO;
import com.example.tap2024.vistas.EmpleadosForm;
import com.example.tap2024.vistas.OrdenForm;
import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCellO extends TableCell<OrdenDAO,String> {

    OrdenDAO objOrden;
    Button btnCelda;
    int opc;
    public ButtonCellO(int opc){
        this.opc =opc;
        String txtButton =(opc==1)?"Editar":"Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }
    private void AccionBoton(int opc) {
        TableView<OrdenDAO> tbvOrden= ButtonCellO.this.getTableView();
        objOrden =tbvOrden.getItems().get(ButtonCellO.this.getIndex());
        if(opc==1){
            //Editar
            new OrdenForm(tbvOrden,objOrden);
        }else{
            //Eliminar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar la orden de: "+objOrden.getFecha()+"?");
            Optional<ButtonType> result =alert.showAndWait();
            if (result.get()==ButtonType.OK){
                objOrden.ELIMINAR();
                tbvOrden.setItems(objOrden.CONSULTAR());
                tbvOrden.refresh();
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
