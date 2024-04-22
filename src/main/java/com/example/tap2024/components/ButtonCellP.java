package com.example.tap2024.components;

import com.example.tap2024.modelos.ProductoDAO;
import com.example.tap2024.vistas.ProductoForm;
import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCellP extends TableCell<ProductoDAO,String> {
    ProductoDAO objPro;
    Button btnCelda;
    int opc;
    public ButtonCellP(int opc){
        this.opc =opc;
        String txtButton =(opc==1)?"Editar":"Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<ProductoDAO> tbvProducto= ButtonCellP.this.getTableView();
        objPro = tbvProducto.getItems().get(ButtonCellP.this.getIndex());
        if(opc==1){
            //Editar
            new ProductoForm(tbvProducto,objPro);
        }else{
            //Eliminar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar el Empleado: "+objPro.getProducto()+"?");
            Optional<ButtonType> result =alert.showAndWait();
            if (result.get()==ButtonType.OK){
                objPro.ELIMINAR();
                tbvProducto.setItems(objPro.CONSULTAR());
                tbvProducto.refresh();
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