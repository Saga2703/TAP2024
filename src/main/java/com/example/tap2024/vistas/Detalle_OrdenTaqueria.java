package com.example.tap2024.vistas;

import com.example.tap2024.components.ButtonCellD;
import com.example.tap2024.components.ButtonCellE;
import com.example.tap2024.modelos.Detalle_OrdenDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Detalle_OrdenTaqueria extends Stage {
    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private Button btnAgregarDetalleOrden;
    private TableView<Detalle_OrdenDAO> tbvDetalleOrden;
    public Detalle_OrdenTaqueria(){
        CrearUI();
        this.setTitle("Taqueria Los Inges");
        this.setScene(escena);
        this.show();

    }

    private void CrearUI(){
        ImageView imvDet =new ImageView(getClass().getResource("/imagenes/employee.png").toString());
        imvDet.setFitWidth(50);
        imvDet.setFitHeight(50);
        btnAgregarDetalleOrden =new Button();
        btnAgregarDetalleOrden.setOnAction(event -> new DetalleOrdenForm(tbvDetalleOrden,null));
        btnAgregarDetalleOrden.setPrefSize(50,50);
        btnAgregarDetalleOrden.setGraphic(imvDet);
        tlbMenu =new ToolBar(btnAgregarDetalleOrden);

        CrearTable();
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvDetalleOrden);
        pnlPrincipal=new Panel("Taqueria");
        pnlPrincipal.setBody(bpnPrincipal);
        pnlPrincipal.getStyleClass().add("panel-primary");
        escena = new Scene(pnlPrincipal,600,300);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)

    }
    private void CrearTable(){
        Detalle_OrdenDAO objDet=new Detalle_OrdenDAO();
        tbvDetalleOrden =new TableView<Detalle_OrdenDAO>();
        TableColumn<Detalle_OrdenDAO,String> tbcIdPro = new TableColumn<>("Id_producto");
        tbcIdPro.setCellValueFactory(new PropertyValueFactory<>("id_producto"));

        TableColumn<Detalle_OrdenDAO,String> tbcIdOr = new TableColumn<>("Id_orden");
        tbcIdOr.setCellValueFactory(new PropertyValueFactory<>("id_orden"));

        TableColumn<Detalle_OrdenDAO,Float> tbcCantidad = new TableColumn<>("Cantidad");
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<Detalle_OrdenDAO,String> tbcPrecio = new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));



        TableColumn<Detalle_OrdenDAO,String> tbcEditar = new TableColumn<Detalle_OrdenDAO,String >("EDITAR");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<Detalle_OrdenDAO, String>, TableCell<Detalle_OrdenDAO, String>>() {
                    @Override
                    public TableCell<Detalle_OrdenDAO, String> call(TableColumn<Detalle_OrdenDAO, String> detalleOrdenDAOStringTableColumn) {
                        return new ButtonCellD(1);
                    }
                }
        );
        TableColumn<Detalle_OrdenDAO,String> tbcEliminar = new TableColumn<Detalle_OrdenDAO,String >("ELIMINAR");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<Detalle_OrdenDAO, String>, TableCell<Detalle_OrdenDAO, String>>() {
                    @Override
                    public TableCell<Detalle_OrdenDAO, String> call(TableColumn<Detalle_OrdenDAO, String> detalleOrdenDAOStringTableColumn) {
                        return new ButtonCellD(2);
                    }
                }
        );

        tbvDetalleOrden.getColumns().addAll(tbcIdPro,tbcIdOr,tbcCantidad,tbcPrecio,tbcEditar,tbcEliminar);
        tbvDetalleOrden.setItems(objDet.CONSULTAR());
    }
}
