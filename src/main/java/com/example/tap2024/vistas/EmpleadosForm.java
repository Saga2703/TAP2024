package com.example.tap2024.vistas;


import com.example.tap2024.modelos.EmpleadosDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmpleadosForm extends Stage {
    private EmpleadosDAO objEmp;
    String[] arPromts = {"Nombre del Empleado","RFC","Sueldo","Telefono","Direccion"};
    private Scene escena;
    private VBox vbxPrincipal;
    private TextField[] arrCampos= new TextField[5];
    private Button btnGuardar;
    TableView<EmpleadosDAO> tbvEmpleados;

    public EmpleadosForm(TableView<EmpleadosDAO>tbvEmp, EmpleadosDAO objEmp){
        tbvEmpleados=tbvEmp;
        this.objEmp =(objEmp==null)? new EmpleadosDAO():objEmp;
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
        btnGuardar.setOnAction(event -> GuardarEmpleado());
        vbxPrincipal.getChildren().add(btnGuardar);
        escena=new Scene(vbxPrincipal,300,250);
    }

    private void LlenarForm() {
        arrCampos[0].setText(objEmp.getEmpleado());
        arrCampos[1].setText(objEmp.getRfc());
        arrCampos[2].setText(objEmp.getSalario()+"");
        arrCampos[3].setText(objEmp.getTelefono());
        arrCampos[4].setText(objEmp.getDireccion());
    }

    private void GuardarEmpleado(){
        objEmp.setEmpleado(arrCampos[0].getText());
        objEmp.setRfc(arrCampos[1].getText());
        objEmp.setSalario(Float.parseFloat(arrCampos[2].getText()));
        objEmp.setTelefono(arrCampos[3].getText());
        objEmp.setDireccion(arrCampos[4].getText());
        objEmp.INSERTAR();
        tbvEmpleados.setItems(objEmp.CONSULTAR());
        tbvEmpleados.refresh();

        arrCampos[0].clear();
        arrCampos[1].clear();
        arrCampos[2].clear();
        arrCampos[3].clear();
        arrCampos[4].clear();
    }
}
