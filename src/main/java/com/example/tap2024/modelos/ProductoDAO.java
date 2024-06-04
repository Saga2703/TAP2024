package com.example.tap2024.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProductoDAO {
    private int id_producto;
    private String producto;
    private float precio;
    private float costo;
    private int id_categoria;
    private byte[] imagen;


    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public byte[] getImagen() { return imagen; }
    public void setImagen(byte[] imagen) { this.imagen = imagen; }


    public void INSERTAR(){
        String query = "insert into producto(producto,precio,costo,id_categoria)"+
                "values('"+producto+"','"+precio+"','"+costo+"','"+id_categoria+"')";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR(){
        String query = "update producto set producto='"+producto+"',"+
                "precio='"+precio+"',"+"costo="+costo+","+"id_categoria='"+id_categoria+"',"+
                "where id_producto="+id_producto;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void ELIMINAR(){
        String query="delete from producto where id_producto="+id_producto;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<ProductoDAO> CONSULTAR(){
        ObservableList<ProductoDAO> listaPro = FXCollections.observableArrayList();
        String query ="select * from producto";
        try{
            ProductoDAO objPro;
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res= stmt.executeQuery(query);
            while (res.next()){
                objPro =new ProductoDAO();
                objPro.id_producto =res.getInt("id_producto");
                objPro.producto=res.getString("producto");
                objPro.precio =res.getFloat("precio");
                objPro.costo =res.getFloat("costo");
                objPro.id_categoria= res.getInt("id_categoria");

                listaPro.add(objPro);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaPro;
    }


}
