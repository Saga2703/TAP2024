package com.example.tap2024.components;

import com.example.tap2024.modelos.EmpleadosDAO;
import com.example.tap2024.vistas.EmpleadosForm;
import javafx.scene.control.*;

import java.util.Optional;

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setHeaderText("Confirmación de acción");
            alert.setContentText("¿Deseas borrar el Empleado: "+objEmp.getEmpleado()+"?");
            Optional<ButtonType> result =alert.showAndWait();
            if (result.get()==ButtonType.OK){
                objEmp.ELIMINAR();
                tbvEmpleados.setItems(objEmp.CONSULTAR());
                tbvEmpleados.refresh();
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
