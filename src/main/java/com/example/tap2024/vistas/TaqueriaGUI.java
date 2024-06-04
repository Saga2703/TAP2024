package com.example.tap2024.vistas;

import com.example.tap2024.PDFTools;
import com.example.tap2024.modelos.*;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.sql.*;


public class TaqueriaGUI extends Stage {
    //
    private int empleadoActual = 1;
    //
    private int mesaActual = 1;//Variable para indicar la mesa en la que se lleva a cabo la orden
    private ArrayList<String> orden = new ArrayList<>();
    //Modelos usados para interactuar con la base de datos
    private EmpleadosDAO empleados = new EmpleadosDAO();
    private ProductoDAO productos = new ProductoDAO();
    private OrdenDAO ordenes = new OrdenDAO();
    private Detalle_OrdenDAO detalleOrden = new Detalle_OrdenDAO();
    private CategoriaDAO categoria = new CategoriaDAO();
    //
    //Elementos graficos de la aplicacion
    Label tMesas = new Label("Mesa actual: " + mesaActual);
    Label tEmpleados = new Label("Empleado actual: " + empleadoActual);
    private Label estadoOrden;
    private VBox listaDeAlimOrden;
    private Scene escena;
    private GridPane mesas = new GridPane();//Grid donde se veran las mesas
    private HBox menuPrivado = new HBox();//Menu para acceder a las opciones del admin (Base de datos)
    private VBox mnMesas = new VBox();
    private HBox mnTiposAlimentos = new HBox();//Meni para seleccionar el tipo de alimento
    private GridPane mnAlimentos = new GridPane();//Menu para seleccionar el alimento
    private HBox bAlimentos = new HBox();//Menu para agregar o quitar alimentos
    private VBox mnOrden = new VBox();
    private HBox mnPrincipal = new HBox();
    private GridPane empleadosGP =  new GridPane();

    public TaqueriaGUI(){
        CrearUI();
        this.setTitle("Taqueria");
        this.setScene(escena);
        this.setMinWidth(720);
        this.setMinHeight(480);
        this.show();
    }

    public void CrearUI(){
        mnPrincipal = new HBox();
        CrearMNOrden();
        CrearMNEmpleados();
        CrearMNMesas();
        escena = new Scene(mnPrincipal);
        escena.getStylesheets().add(getClass().getResource("/Estilos/taqueria.css").toString());
    }

    //Menu para las ordenes
    public void CrearMNOrden(){
        Label tOrden = new Label("Orden");
        CrearMNTiposAlimentos();
        CrearBAlimentos();
        mnOrden = new VBox(tOrden,mnTiposAlimentos,mnAlimentos,bAlimentos);
        mnPrincipal.getChildren().add(mnOrden);
    }

    public void CrearMNTiposAlimentos(){
        Image imgTipo;
        ImageView imgTipoView;
        Button bTipo;
        VBox temp = new VBox();
        mnTiposAlimentos = new HBox();
        ObservableList<CategoriaDAO> tipos = categoria.CONSULTAR();
        for(int tipo = 0; tipo < tipos.size(); tipo++){
            imgTipo = ObtenerImg.obtImg(tipos.get(tipo).getCategoria());
            imgTipoView = new ImageView(imgTipo);
            imgTipoView.setFitHeight(200);
            imgTipoView.setFitWidth(200);
            bTipo = new Button(tipos.get(tipo).getCategoria());
            bTipo.setPrefSize(200,50);
            int tempS = tipos.get(tipo).getId_categoria();
            bTipo.setOnAction(event -> {CrearMNAlimentos(tempS);});
            temp = new VBox(imgTipoView,bTipo);
            mnTiposAlimentos.getChildren().add(temp);
        }
    }

    public void CrearMNAlimentos(int categoria){
        mnAlimentos.getChildren().remove(0,mnAlimentos.getChildren().size());
        Image imgAlim;
        ImageView imgAlimView;
        Button bAlim;
        VBox temp;
        ObservableList<ProductoDAO> alimentos = productos.CONSULTAR();
        for(int alimento = 0; alimento < alimentos.size(); alimento++){
            imgAlim = ObtenerImg.obtImg(alimentos.get(alimento).getProducto());
            imgAlimView = new ImageView(imgAlim);
            imgAlimView.setFitHeight(150);
            imgAlimView.setFitWidth(150);
            bAlim = new Button(alimentos.get(alimento).getProducto());
            int finalAlimento = alimento;
            bAlim.setOnAction(event -> agregarAOrden(alimentos, finalAlimento));
            bAlim.setPrefSize(150,50);
            temp = new VBox(imgAlimView, bAlim);
            if(alimentos.get(alimento).getId_categoria() == categoria) {
                mnAlimentos.add(temp, alimento % 5, alimento / 5);
            }
        }
    }

    public void agregarAOrden(ObservableList<ProductoDAO> alimentos, int finalAlimento ){
        listaDeAlimOrden.getChildren().add(new Label(alimentos.get(finalAlimento).getProducto()));
        orden.add(alimentos.get(finalAlimento).getProducto());
    }

    public void CrearBAlimentos(){
        estadoOrden = new Label("Orden actual: ");
        listaDeAlimOrden = new VBox(estadoOrden);
        Button removeAlim = new Button("Remover");
        removeAlim.setOnAction(event -> removerDeOrden());
        Button submitOrder = new Button("Realizar orden");
        submitOrder.setOnAction(event -> realizarOrden());
        VBox bAlimOrder = new VBox(removeAlim,submitOrder);
        bAlimentos = new HBox(listaDeAlimOrden,bAlimOrder);
    }

    public void removerDeOrden(){
        if(listaDeAlimOrden.getChildren().size() > 1) {
            listaDeAlimOrden.getChildren().remove(listaDeAlimOrden.getChildren().size() - 1);
            orden.remove(orden.size()-1);
        }
    }

    public void realizarOrden(){
        String fecha = Calendar.getInstance().getTime().toString();
        String historialParaTicket = "";
        for(int i = 0; i < empleados.CONSULTAR().size();i++){
            if(empleados.CONSULTAR().get(i).getId_empleado() == empleadoActual){
                historialParaTicket = historialParaTicket + "\nAtendio " + empleados.CONSULTAR().get(i).getEmpleado()+"\n";
            }
        }
        historialParaTicket = historialParaTicket + "\nEn la mesa " + mesaActual + "\n";
        historialParaTicket = historialParaTicket + "\nEl dia "+fecha+"\n";
        historialParaTicket = historialParaTicket + "\n\nOrden:\n";
        HashMap<String, Integer> numPerPro = new HashMap<>();
        HashMap<String, Integer> idPerPro = new HashMap<>();
        HashMap<String, Float> prePerPro = new HashMap<>();
         if(orden != null && orden.size() >0) {
             //Crear HashMap con el numero de veces que cada producto se repite en la orden
            for (int i = 0; i < orden.size(); i++) {
                if(numPerPro.containsKey(orden.get(i))) {
                    int temp = numPerPro.get(orden.get(i));
                    numPerPro.put(orden.get(i), temp + 1);
                }else{
                    numPerPro.put(orden.get(i),1);
                }
            }
            //Conseguir ids y precios de los productos
             ObservableList<ProductoDAO> alimentos = productos.CONSULTAR();
             for(int alimento = 0; alimento < alimentos.size(); alimento++){
                if(numPerPro.containsKey(alimentos.get(alimento).getProducto())){
                    String tempProducto = alimentos.get(alimento).getProducto();
                    historialParaTicket = historialParaTicket + "\n" + tempProducto + "  ";
                    String tempPrice = String.valueOf(alimentos.get(alimento).getPrecio());
                    historialParaTicket = historialParaTicket + tempPrice + "\n";
                    idPerPro.put(alimentos.get(alimento).getProducto(),alimentos.get(alimento).getId_producto());
                    prePerPro.put(alimentos.get(alimento).getProducto(),alimentos.get(alimento).getPrecio());
                }
             }
             //Crear orden
             OrdenDAO temp = new OrdenDAO();
             int ordenActual = ordenes.CONSULTAR().get(ordenes.CONSULTAR().size()-1).getId_orden()+1;
             System.out.println("La ultima orden es: "+ordenActual);
             temp.setId_empleado(empleadoActual);
             try {
                 DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                 Date sqlDate = new Date(df.parse("02-06-2024").getTime());
                 temp.setFecha(sqlDate);
             }catch(Exception e){
                 e.printStackTrace();
             }
             temp.setObservaciones("Mesa: " + mesaActual);
             temp.INSERTAR();
             //Crear detalle_orden
             numPerPro.forEach(
                     (k,v) -> {
                         Detalle_OrdenDAO tempD = new Detalle_OrdenDAO();
                         tempD.setId_orden(ordenActual);
                         tempD.setId_producto(idPerPro.get(k));
                         tempD.setCantidad(v);
                         tempD.setPrecio(prePerPro.get(k)*v);
                         tempD.INSERTAR();
                     }
            );
            System.out.println(orden.toString());
            orden = new ArrayList<>();
            System.out.println(orden.toString());
            listaDeAlimOrden.getChildren().remove(1, listaDeAlimOrden.getChildren().size());
            PDFTools.generarTicketDeCompra(historialParaTicket);
        }
    }

    public void CrearMNMesas(){
        CrearMesas();
        CrearMenuPrivado();
        mnMesas = new VBox(tMesas, mesas,tEmpleados,empleadosGP, menuPrivado);
        mnPrincipal.getChildren().add(mnMesas);
    }

    public void CrearMesas(){
        Button[] bMesa = new Button[12];
        mesas = new GridPane();
        for(int mesa = 0; mesa < 12; mesa++){
            bMesa[mesa] = new Button((String.valueOf(mesa + 1)));
            bMesa[mesa].setPrefSize(100,100);
            int finalMesa = mesa + 1;
            bMesa[mesa].setOnAction(event -> {this.mesaActual = finalMesa;tMesas.setText("Mesa actual: "+mesaActual);});
            mesas.add(bMesa[mesa],mesa%3,mesa/3);
        }
    }

    public void CrearMNEmpleados(){
        ObservableList<EmpleadosDAO> empleadosTemp = empleados.CONSULTAR();
        Button[] bEmpleado = new Button[empleadosTemp.size()];
        empleadosGP = new GridPane();
        for(int i = 0; i < empleadosTemp.size(); i++){
            bEmpleado[i] = new Button(String.valueOf(empleadosTemp.get(i).getId_empleado()));
            bEmpleado[i].setPrefSize(100,100);
            final int temp = empleadosTemp.get(i).getId_empleado();
            bEmpleado[i].setOnAction(event ->{
                this.empleadoActual = temp;
                System.out.println("Empleado actual cambio a: " + empleadoActual);
                tEmpleados.setText("Empleado actual: " + empleadoActual);
            });
            empleadosGP.add(bEmpleado[i],i%3,i/3);
        }
    }

    public void CrearMenuPrivado(){
        Button mnPriv = new Button("Admin Tools");
        mnPriv.setOnAction(event -> new VerificacionGUI());
        menuPrivado = new HBox(mnPriv);
    }
}
