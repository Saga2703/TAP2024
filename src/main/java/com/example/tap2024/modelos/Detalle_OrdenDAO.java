package com.example.tap2024.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class Detalle_OrdenDAO {
    private int id_producto;
    private int id_orden;
    private int cantidad;
    private float precio;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void INSERTAR(){
        String query = "insert into detalle_orden(id_producto,id_orden,cantidad,precio)"+
                "values('"+id_producto+"','"+id_orden+"','"+cantidad+"','"+precio+"')";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ACTUALIZAR(){
        String query = "update detalle_orden set id_producto='"+id_producto+"',"+
                "id_orden='"+id_orden+"',"+"cantidad="+cantidad+","+"precio='"+precio+"'"+
                "where id_producto="+id_producto +"and id_orden="+id_orden;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void ELIMINAR(){
        String query="delete from detalle_orden where id_producto="+id_producto + "and id_orden"+id_orden;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<Detalle_OrdenDAO> CONSULTAR(){
        ObservableList<Detalle_OrdenDAO> listaDO = FXCollections.observableArrayList();
        String query ="select * from detalle_orden";
        try{
            Detalle_OrdenDAO objDO;
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res= stmt.executeQuery(query);
            while (res.next()){
                objDO =new Detalle_OrdenDAO();
                objDO.id_producto =res.getInt("id_producto");
                objDO.id_orden=res.getInt("id_orden");
                objDO.cantidad =res.getInt("cantidad");
                objDO.precio =res.getFloat("precio");

                listaDO.add(objDO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaDO;
    }
}
