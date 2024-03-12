package com.example.tap2024.vistas;

import com.example.tap2024.components.ButtonCell;
import com.example.tap2024.modelos.EmpleadosDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EmpleadoTaqueria extends Stage {
    private VBox vbxPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private Button btnAgregarEmpleado;
    private TableView<EmpleadosDAO> tbvEmpleados;
    public EmpleadoTaqueria(){
        CrearUI();
        this.setTitle("Taqueria Los Inges");
        this.setScene(escena);
        this.show();

    }

    private void CrearUI(){
        ImageView imvEmp =new ImageView(getClass().getResource("/imagenes/employee.png").toString());
        imvEmp.setFitWidth(50);
        imvEmp.setFitHeight(50);
        btnAgregarEmpleado =new Button();
        btnAgregarEmpleado.setOnAction(event -> new EmpleadosForm(tbvEmpleados));
        btnAgregarEmpleado.setPrefSize(50,50);
        btnAgregarEmpleado.setGraphic(imvEmp);
        tlbMenu =new ToolBar(btnAgregarEmpleado);

        CrearTable();
        vbxPrincipal = new VBox(tlbMenu,tbvEmpleados);
        escena = new Scene(vbxPrincipal,600,300);
    }

    private void CrearTable(){
        EmpleadosDAO objEmp=new EmpleadosDAO();
        tbvEmpleados =new TableView<EmpleadosDAO>();
        TableColumn<EmpleadosDAO,String> tbcNomEmp = new TableColumn<>("Empleado");
        tbcNomEmp.setCellValueFactory(new PropertyValueFactory<>("empleado"));

        TableColumn<EmpleadosDAO,String> tbcRfc = new TableColumn<>("RFC");
        tbcRfc.setCellValueFactory(new PropertyValueFactory<>("rfc"));

        TableColumn<EmpleadosDAO,Float> tbcSalario = new TableColumn<>("Salario");
        tbcSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));

        TableColumn<EmpleadosDAO,String> tbcTelefono = new TableColumn<>("Telefono");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<EmpleadosDAO,String> tbcDireccion = new TableColumn<>("Direccion");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<EmpleadosDAO,String> tbcEditar = new TableColumn<EmpleadosDAO,String >("EDITAR");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<EmpleadosDAO, String>, TableCell<EmpleadosDAO, String>>() {
                    @Override
                    public TableCell<EmpleadosDAO, String> call(TableColumn<EmpleadosDAO, String> empleadosDAOStringTableColumn) {
                        return new ButtonCell();
                    }
                }
        );

        tbvEmpleados.getColumns().addAll(tbcNomEmp,tbcRfc,tbcSalario,tbcTelefono,tbcDireccion,tbcEditar);
        tbvEmpleados.setItems(objEmp.CONSULTAR());
    }
}
