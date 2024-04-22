package com.example.tap2024.vistas;

import com.example.tap2024.components.ButtonCell;
import com.example.tap2024.modelos.CategoriaDAO;
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

public class CategoriaTaqueria extends Stage {
    private Panel pnlPrincipal;
    private BorderPane bpnPrincipal;
    private ToolBar tlbMenu;
    private Scene escena;
    private Button btnAgregarCategoria;
    private TableView<CategoriaDAO> tbvCategoria;
    public CategoriaTaqueria(){
        CrearUI();
        this.setTitle("Taqueria Los Inges");
        this.setScene(escena);
        this.show();

    }
    private void CrearUI(){
        ImageView imvEmp =new ImageView(getClass().getResource("/imagenes/employee.png").toString());
        imvEmp.setFitWidth(50);
        imvEmp.setFitHeight(50);
        btnAgregarCategoria =new Button();
        btnAgregarCategoria.setOnAction(event -> new CategoriaForm(tbvCategoria,null));
        btnAgregarCategoria.setPrefSize(50,50);
        btnAgregarCategoria.setGraphic(imvEmp);
        tlbMenu =new ToolBar(btnAgregarCategoria);

        CrearTable();
        bpnPrincipal = new BorderPane();
        bpnPrincipal.setTop(tlbMenu);
        bpnPrincipal.setCenter(tbvCategoria);
        pnlPrincipal=new Panel("Taqueria");
        pnlPrincipal.setBody(bpnPrincipal);
        pnlPrincipal.getStyleClass().add("panel-primary");
        escena = new Scene(pnlPrincipal,600,300);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)

    }

    private void CrearTable(){
        CategoriaDAO objCat=new CategoriaDAO();
        tbvCategoria =new TableView<CategoriaDAO>();
        TableColumn<CategoriaDAO,String> tbcCategoria = new TableColumn<>("Categoria");
        tbcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));



        TableColumn<CategoriaDAO,String> tbcEditar = new TableColumn<CategoriaDAO,String >("EDITAR");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<CategoriaDAO, String>, TableCell<CategoriaDAO, String>>() {
                    @Override
                    public TableCell<CategoriaDAO, String> call(TableColumn<CategoriaDAO, String> productoDAOStringTableColumn) {
                        return new ButtonCell(1);
                    }
                }
        );
        TableColumn<CategoriaDAO,String> tbcEliminar = new TableColumn<CategoriaDAO,String >("ELIMINAR");
        tbcEliminar.setCellFactory(
                new Callback<TableColumn<CategoriaDAO, String>, TableCell<CategoriaDAO, String>>() {
                    @Override
                    public TableCell<CategoriaDAO, String> call(TableColumn<CategoriaDAO, String> productoDAOStringTableColumn) {
                        return new ButtonCell(2);
                    }
                }
        );

        tbvCategoria.getColumns().addAll(tbcCategoria,tbcEditar,tbcEliminar);
        tbvCategoria.setItems(objCat.CONSULTAR());
    }
}