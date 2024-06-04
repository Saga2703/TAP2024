package com.example.tap2024.vistas;

import com.example.tap2024.modelos.ProductoDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ProductoForm extends Stage {
    private ProductoDAO objPro;
    String[] arPromts = {"Producto","Precio","Costo","id_Categoria"};
    private Scene escena;
    private VBox vbxPrincipal;
    private TextField[] arrCampos= new TextField[4];
    private Button btnGuardar;
    private Button btnCargarImagen;

    TableView<ProductoDAO> tbvProducto;
    public ProductoForm(TableView<ProductoDAO> tbvPro, ProductoDAO objPro) {
        tbvProducto=tbvPro;
        this.objPro =(objPro==null)? new ProductoDAO():objPro;
        CrearUI();
        escena.getStylesheets().add(getClass().getResource("/Estilos/taqueria.css").toString());
        this.setTitle("Insertar Producto");
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
        btnGuardar.setOnAction(event -> GuardarProducto());
        btnCargarImagen = new Button("Cargar Imagen");
        btnCargarImagen.setOnAction(event -> cargarImagen());
        vbxPrincipal.getChildren().addAll(btnGuardar,btnCargarImagen);
        escena=new Scene(vbxPrincipal,300,250);
    }

    private void LlenarForm() {
        arrCampos[0].setText(objPro.getProducto());
        arrCampos[1].setText(objPro.getPrecio()+"");
        arrCampos[2].setText(objPro.getCosto()+"");
        arrCampos[3].setText(objPro.getId_categoria()+"");

    }
    private void GuardarProducto(){
        objPro.setProducto(arrCampos[0].getText());
        objPro.setPrecio(Float.parseFloat(arrCampos[1].getText()));
        objPro.setCosto(Float.parseFloat(arrCampos[2].getText()));
        objPro.setId_categoria(Integer.parseInt(arrCampos[3].getText()));

        if(objPro.getId_producto()>0){
            objPro.ACTUALIZAR();
        }
        else
            objPro.INSERTAR();
        tbvProducto.setItems(objPro.CONSULTAR());
        tbvProducto.refresh();

        arrCampos[0].clear();
        arrCampos[1].clear();
        arrCampos[2].clear();
        arrCampos[3].clear();
    }
    private void cargarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        File file = fileChooser.showOpenDialog(this);

        if (file != null) {
            try (FileInputStream fis = new FileInputStream(file)) {
                // Lógica para cargar la imagen en la base de datos
                byte[] imagen = fis.readAllBytes();
                objPro.setImagen(imagen);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
