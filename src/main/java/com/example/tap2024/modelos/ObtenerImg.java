package com.example.tap2024.modelos;

import javafx.scene.image.Image;

import java.io.FileInputStream;

public class ObtenerImg {

    public static Image obtImg(String nomImg){
        //
        System.out.println(nomImg);
        Image temp = null;
        try {
            FileInputStream inputstream = new FileInputStream("../../IdeaProjects/TAP2024/src/main/resources/imagenes/sheik.jpg");
            Image image = new Image(inputstream);
            temp = image;
        }catch(Exception e){
            e.printStackTrace();
        }
        return temp;
    }
}
//src/main/java/com/example/tap2024/modelos/ObtenerImg.java
//"C:\Users\jahmc\IdeaProjects\TAP2024-emmanuel" C:/Users/jahmc