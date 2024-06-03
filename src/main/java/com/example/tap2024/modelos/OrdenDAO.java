package com.example.tap2024.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
public class OrdenDAO {
    private int id_orden;
    private int id_empleado;
    private Date fecha;
    private String observaciones;

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void INSERTAR(){
        String query = "insert into orden(id_empleado,fecha,observaciones)"+
                "values('"+id_empleado+"','"+fecha+"','"+observaciones+"')";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR(){
        String query = "update orden set id_empleado='"+id_empleado+"',"+
                "fecha='"+fecha+"',"+"observaciones="+observaciones;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void ELIMINAR(){
        String query="delete from orden where id_orden="+id_orden;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<OrdenDAO> CONSULTAR(){
        ObservableList<OrdenDAO> listaOrd = FXCollections.observableArrayList();
        String query ="select * from orden";
        try{
            OrdenDAO objOrden;
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res= stmt.executeQuery(query);
            while (res.next()){
                objOrden = new OrdenDAO();
                objOrden.id_orden = res.getInt("id_orden");
                objOrden.id_empleado =res.getInt("id_empleado");
                objOrden.fecha=res.getDate("fecha");
                objOrden.observaciones =res.getString("observaciones");

                listaOrd.add(objOrden);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaOrd;
    }
}
