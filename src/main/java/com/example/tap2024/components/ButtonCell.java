package com.example.tap2024.components;

import com.example.tap2024.modelos.EmpleadosDAO;
import com.example.tap2024.vistas.EmpleadosForm;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

public class ButtonCell extends TableCell<EmpleadosDAO,String> {
    EmpleadosDAO objEmp;
    Button btnCelda;
    int opc;
    public ButtonCell(int opc){
        this.opc =opc;
        String txtButton =(opc==1)?"Editar":"Eliminar";
        btnCelda = new Button(txtButton);
        btnCelda.setOnAction(event -> AccionBoton(opc));
    }

    private void AccionBoton(int opc) {
        TableView<EmpleadosDAO>tbvEmpleados= ButtonCell.this.getTableView();
        objEmp = tbvEmpleados.getItems().get(ButtonCell.this.getIndex());
        if(opc==1){
            //Editar
            new EmpleadosForm(tbvEmpleados,objEmp);
        }else{
            //Eliminar
        }
    }

    @Override
    protected void updateItem(String item,boolean empty){
        super.updateItem(item,empty);
        if (!empty)
            this.setGraphic(btnCelda);
    }
}
