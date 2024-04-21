package com.example.tap2024.modelos;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class ObtenerImg {

    public static Image obtImg(String nomImg){
        Image temp = null;
        try {
            FileInputStream inputstream = new FileInputStream("/home/angel/Desktop/TAP2024/src/main/resources/imagenes/sheik.jpg");
            Image image = new Image(inputstream);
            temp = image;
        }catch(Exception e){
            e.printStackTrace();
        }
        return temp;
    }
}
