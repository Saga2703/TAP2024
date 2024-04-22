package com.example.tap2024.vistas;

import com.example.tap2024.components.ButtonCellE;
import com.example.tap2024.components.ButtonCellO;
import com.example.tap2024.modelos.OrdenDAO;
import com.example.tap2024.modelos.ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class OrdenTaqueria extends Stage {

    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private Button btnAgregarOrden;
    private TableView<OrdenDAO> tbvOrden;
    public OrdenTaqueria(){
        CrearUI();
        escena.getStylesheets().add(getClass().getResource("/Estilos/taqueria.css").toString());
        this.setTitle("Taqueria Los Inges");
        this.setScene(escena);
        this.show();

    }
    private void CrearUI(){
        ImageView imvEmp =new ImageView(getClass().getResource("/imagenes/employee.png").toString());
        imvEmp.setFitWidth(50);
        imvEmp.setFitHeight(50);
        btnAgregarOrden =new Button();
        btnAgregarOrden.setOnAction(event -> new OrdenForm(tbvOrden,null));
        btnAgregarOrden.setPrefSize(50,50);
        btnAgregarOrden.setGraphic(imvEmp);
        tlbMenu =new ToolBar(btnAgregarOrden);

        CrearTable();
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvOrden);
        pnlPrincipal=new Panel("Taqueria");
        pnlPrincipal.setBody(bpnPrincipal);
        pnlPrincipal.getStyleClass().add("panel-primary");
        escena = new Scene(pnlPrincipal,600,300);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)

    }

    private void CrearTable(){
        OrdenDAO objPro=new OrdenDAO();
        tbvOrden =new TableView<OrdenDAO>();
        TableColumn<OrdenDAO,String> tbcIdEmp = new TableColumn<>("Id_empleado");
        tbcIdEmp.setCellValueFactory(new PropertyValueFactory<>("id_empleado"));

        TableColumn<OrdenDAO,String> tbcFecha = new TableColumn<>("Fecha");
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<OrdenDAO,Float> tbcObs = new TableColumn<>("Observaciones");
        tbcObs.setCellValueFactory(new PropertyValueFactory<>("observaciones"));





        TableColumn<OrdenDAO,String> tbcEditar = new TableColumn<OrdenDAO,String >("EDITAR");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<OrdenDAO, String>, TableCell<OrdenDAO, String>>() {
                    @Override
                    public TableCell<OrdenDAO, String> call(TableColumn<OrdenDAO, String> ordenDAOStringTableColumn) {
                        return new ButtonCellO(1);
                    }
                }
        );
        TableColumn<OrdenDAO,String> tbcEliminar = new TableColumn<OrdenDAO,String >("ELIMINAR");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<OrdenDAO, String>, TableCell<OrdenDAO, String>>() {
                    @Override
                    public TableCell<OrdenDAO, String> call(TableColumn<OrdenDAO, String> ordenDAOStringTableColumn) {
                        return new ButtonCellO(2);
                    }
                }
        );

        tbvOrden.getColumns().addAll(tbcIdEmp,tbcFecha,tbcObs,tbcEditar,tbcEliminar);
        tbvOrden.setItems(objPro.CONSULTAR());
    }


}
