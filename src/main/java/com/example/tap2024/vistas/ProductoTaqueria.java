package com.example.tap2024.vistas;

import com.example.tap2024.components.ButtonCellE;
import com.example.tap2024.components.ButtonCellP;
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

public class ProductoTaqueria extends Stage {

    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private Button btnAgregarProducto;
    private TableView<ProductoDAO> tbvProducto;
    public ProductoTaqueria(){
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
        btnAgregarProducto =new Button();
        btnAgregarProducto.setOnAction(event -> new ProductoForm(tbvProducto,null));
        btnAgregarProducto.setPrefSize(50,50);
        btnAgregarProducto.setGraphic(imvEmp);
        tlbMenu =new ToolBar(btnAgregarProducto);

        CrearTable();
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvProducto);
        pnlPrincipal=new Panel("Taqueria");
        pnlPrincipal.setBody(bpnPrincipal);
        pnlPrincipal.getStyleClass().add("panel-primary");
        escena = new Scene(pnlPrincipal,600,300);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)

    }

    private void CrearTable(){
        ProductoDAO objPro=new ProductoDAO();
        tbvProducto =new TableView<ProductoDAO>();
        TableColumn<ProductoDAO,String> tbcProducto = new TableColumn<>("Producto");
        tbcProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));

        TableColumn<ProductoDAO,String> tbcPrecio = new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<ProductoDAO,Float> tbcCosto = new TableColumn<>("Costo");
        tbcCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        TableColumn<ProductoDAO,Float> tbcIdCat = new TableColumn<>("Id_categoria");
        tbcIdCat.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));

        TableColumn<ProductoDAO,Float> tbcImagen = new TableColumn<>("Imagen");
        tbcIdCat.setCellValueFactory(new PropertyValueFactory<>("imagen"));

        TableColumn<ProductoDAO,String> tbcEditar = new TableColumn<ProductoDAO,String >("EDITAR");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<ProductoDAO, String>, TableCell<ProductoDAO, String>>() {
                    @Override
                    public TableCell<ProductoDAO, String> call(TableColumn<ProductoDAO, String> productoDAOStringTableColumn) {
                        return new ButtonCellP(1);
                    }
                }
        );
        TableColumn<ProductoDAO,String> tbcEliminar = new TableColumn<ProductoDAO,String >("ELIMINAR");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<ProductoDAO, String>, TableCell<ProductoDAO, String>>() {
                    @Override
                    public TableCell<ProductoDAO, String> call(TableColumn<ProductoDAO, String> productoDAOStringTableColumn) {
                        return new ButtonCellP(2);
                    }
                }
        );



        tbvProducto.getColumns().addAll(tbcProducto,tbcPrecio,tbcCosto,tbcIdCat,tbcImagen,tbcEditar,tbcEliminar);
        tbvProducto.setItems(objPro.CONSULTAR());
    }


}
