package com.example.tap2024.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadosDAO {
    private int id_empleado;
    private String empleado;
    private String rfc;
    private float salario;
    private String telefono;
    private String direccion;

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    private void INSERTAR(){
         String query = "insert into Empleado(empleado,rfc,salario,telefono,direccion)"+
                 "values('"+empleado+"','"+rfc+"','"+salario+"','"+telefono+"','"+direccion+"')";
         try {
             Statement stmt = Conexion.connection.createStatement();
             stmt.executeUpdate(query);
         }catch (Exception e){
             e.printStackTrace();
         }
    }
    private void ACTUALIZAR(){
        String query = "update Empleado set empleado='"+empleado+"',"+
        "rfc='"+rfc+"',"+"salario="+salario+","+"telefono='"+telefono+"',"+"direccion='"+direccion+"'"+
                "where id_empleado="+id_empleado;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void ELIMINAR(){
        String query="delete from empleado where id_empleado="+id_empleado;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<EmpleadosDAO> CONSULTAR(){
        ObservableList<EmpleadosDAO> listaEmp = FXCollections.observableArrayList();
        String query ="select * from empleado";
        try{
            EmpleadosDAO objEmp;
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res= stmt.executeQuery(query);
            while (res.next()){
                objEmp =new EmpleadosDAO();
                objEmp.id_empleado =res.getInt("id_empleado");
                objEmp.empleado=res.getString("empleado");
                objEmp.rfc =res.getString("rfc");
                objEmp.salario =res.getFloat("salario");
                objEmp.telefono=res.getString("telefono");
                objEmp.direccion=res.getString("direccion");
                listaEmp.add(objEmp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaEmp;
    }
}
