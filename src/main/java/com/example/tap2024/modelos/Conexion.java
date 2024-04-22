package com.example.tap2024.modelos;
import java.sql.Connection;
import java.sql.DriverManager;

//taqueria admin 1234
public class Conexion {
    static private String DB="taqueria";
    static private String USER="root";
    static private String PWD="april2903";
    static public Connection connection;
    public static void  crearConexion(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+
                    DB+"?allowPublicKeyRetrieval=true&useSSL=false",USER,PWD);
            System.out.println("Conexion establecida con exito!!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
