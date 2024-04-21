package com.example.tap2024.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriaDAO {
    private int id_categoria;
    private String categoria;

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void INSERTAR(){
        String query = "insert into categoria(categoria)"+
                "values('"+categoria+"')";
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ACTUALIZAR(){
        String query = "update categoria set categoria='"+categoria+"',"+
                "where id_categoria="+id_categoria;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void ELIMINAR(){
        String query="delete from categoria where id_categoria="+id_categoria;
        try{
            Statement stmt= Conexion.connection.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<CategoriaDAO> CONSULTAR(){
        ObservableList<CategoriaDAO> listaCat = FXCollections.observableArrayList();
        String query ="select * from Categoria";
        try{
            CategoriaDAO objCat;
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res= stmt.executeQuery(query);
            while (res.next()){
                objCat =new CategoriaDAO();
                objCat.id_categoria =res.getInt("id_categoria");
                objCat.categoria =res.getString("categoria");
                listaCat.add(objCat);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaCat;
    }
}
