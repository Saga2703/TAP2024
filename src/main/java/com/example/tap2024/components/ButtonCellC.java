package com.example.tap2024.components;

import com.example.tap2024.modelos.CategoriaDAO;
import com.example.tap2024.modelos.EmpleadosDAO;
import com.example.tap2024.vistas.CategoriaForm;
import com.example.tap2024.vistas.EmpleadosForm;
import javafx.scene.control.*;

import java.util.Optional;

public class ButtonCellC extends TableCell<CategoriaDAO,String> {
    CategoriaDAO objCat;
    Button btnCelda;
    int opc;
    public ButtonCellC(int opc){
        this.opc =opc;
        String txtButton =(opc==1)?"Editar":"Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<CategoriaDAO> tbvCategoria= ButtonCellC.this.getTableView();
        objCat = tbvCategoria.getItems().get(ButtonCellC.this.getIndex());
        if(opc==1){
            //Editar
            new CategoriaForm(tbvCategoria,objCat);
        }else{
            //Eliminar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar la categoria: "+objCat.getCategoria()+"?");
            Optional<ButtonType> result =alert.showAndWait();
            if (result.get()==ButtonType.OK){
                objCat.ELIMINAR();
                tbvCategoria.setItems(objCat.CONSULTAR());
                tbvCategoria.refresh();
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
