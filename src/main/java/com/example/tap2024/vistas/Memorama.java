package com.example.tap2024.vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Memorama extends Stage {
    private Scene escena;
    private VBox vContenedor1, vContenedor2;
    private HBox hContenedor1,hContenedor2, hContenedor3,hContenedor4;
    private Button boton=new Button("Empezar");
    private TextField txtPantalla;
    private GridPane gdpImagenes;
    private Label lblPares, lbl1,lbl2,lbl3,lbl4,lblTimer;
    private BorderPane bdpAll;
    private int numPares = 3; // Número de pares de imágenes
    private ArrayList<String> listaImagenes;


    public Memorama(){
        CrearUI();
        this.setTitle("Memorama ");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI() {
        lblPares = new Label("Número de pares:");
        txtPantalla = new TextField();
        lblTimer = new Label("00:00");
        gdpImagenes = new GridPane();
        boton.setOnAction(e -> actualizarNumParesYRevolver());

        hContenedor1 = new HBox(lblPares, txtPantalla, boton, lblTimer);
        hContenedor1.setSpacing(10);
        hContenedor1.setPadding(new Insets(10));

        lbl1 = new Label("Jugador 1");
        lbl2 = new Label("Jugador 2");
        lbl3 = new Label("...");
        lbl4 = new Label("...");

        hContenedor3 = new HBox(lbl1, lbl3);
        hContenedor3.setSpacing(20);
        hContenedor3.setPadding(new Insets(10));

        hContenedor4 = new HBox(lbl2, lbl4);
        hContenedor4.setSpacing(20);
        hContenedor4.setPadding(new Insets(10));

        vContenedor2 = new VBox(hContenedor3, hContenedor4);
        vContenedor2.setPadding(new Insets(10));

        hContenedor2 = new HBox(gdpImagenes, vContenedor2);
        hContenedor2.setSpacing(50);
        hContenedor2.setPadding(new Insets(10));

        vContenedor1 = new VBox(hContenedor1, hContenedor2);
        vContenedor1.setSpacing(10);
        vContenedor1.setPadding(new Insets(10));

        bdpAll = new BorderPane(vContenedor1);
        escena = new Scene(bdpAll, 800, 600);

        RevolverCartas(0); // Inicialmente revuelve las cartas con 8 pares
    }

    private void actualizarNumParesYRevolver() {
        try {
            int nuevoNumPares = Integer.parseInt(txtPantalla.getText());
            if (nuevoNumPares < 3 || nuevoNumPares > 15) {
                throw new NumberFormatException();
            }
            RevolverCartas(nuevoNumPares);
        } catch (NumberFormatException e) {
            mostrarError("Ingrese un número válido entre 3 y 15.");

        }
    }
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void RevolverCartas(int numPares) {
        gdpImagenes.getChildren().clear();

        String[] arImagenes = {
                "link oot.jpg", "sheik.jpg", "zelda.jpg", "trifuerza.jpg",
                "fay.jpg", "guardian.jpg", "link breath.jpg", "link sky.jpg",
                "zelda S.jpg", "ganon.jpg", "goron.jpg", "mipha.jpg",
                "revali.jpg", "urbosa.jpg", "lobo.jpg"
        };

        listaImagenes = new ArrayList<>();
        for (int i = 0; i < numPares; i++) {
            listaImagenes.add(arImagenes[i]);
            listaImagenes.add(arImagenes[i]);
        }
        Collections.shuffle(listaImagenes);

        Button[][] arBtncartas = new Button[4][numPares];

        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numPares; j++) {
                if (pos < listaImagenes.size()) {
                    arBtncartas[i][j] = new Button();

                    // Load image from listaImagenes
                    String imageName = listaImagenes.get(pos);
                    Image image = null;
                    try {
                        image = new Image(getClass().getResource("/imagenes/" + imageName).toString());
                    } catch (Exception e) {
                        mostrarError("No se pudo cargar la imagen: " + imageName);
                    }

                    if (image != null) {
                        ImageView imageView = new ImageView(image);
                        imageView.setFitHeight(150);
                        imageView.setFitWidth(100);

                        arBtncartas[i][j].setGraphic(imageView);
                    }
                    arBtncartas[i][j].setPrefSize(100, 150);
                    gdpImagenes.add(arBtncartas[i][j], j, i);
                    pos++;
                }
            }
        }
    }
}
