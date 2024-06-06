package com.example.tap2024.vistas;

import javafx.application.Platform;
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
    private ArrayList<String> listaImagenes;
    private String reversoPath = "/imagenes/reverso.jpg";
    private int turnoActual = 1;
    private int paresJugador1 = 0;
    private int paresJugador2 = 0;
    private Button primeraCarta;
    private Button segundaCarta;
    private boolean esperandoVolteo = false;


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
        hContenedor3.setStyle("-fx-background-color: white;");

        hContenedor3.setSpacing(20);
        hContenedor3.setPadding(new Insets(10));

        hContenedor4 = new HBox(lbl2, lbl4);
        hContenedor4.setStyle("-fx-background-color: white;");

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


    }

    private void actualizarNumParesYRevolver() {
        hContenedor3.setStyle("-fx-background-color: white;");
        hContenedor4.setStyle("-fx-background-color: white;");
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
        resetGame();

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

        Image reversoImage = new Image(getClass().getResource(reversoPath).toString());
        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numPares; j++) {
                if (pos < listaImagenes.size()) {
                    arBtncartas[i][j] = new Button();
                    ImageView reversoView = new ImageView(reversoImage);
                    reversoView.setFitHeight(150);
                    reversoView.setFitWidth(100);
                    arBtncartas[i][j].setGraphic(reversoView);
                    arBtncartas[i][j].setPrefSize(100, 150);

                    String imageName = listaImagenes.get(pos);
                    ImageView frenteView = new ImageView(new Image(getClass().getResource("/imagenes/" + imageName).toString()));
                    frenteView.setFitHeight(150);
                    frenteView.setFitWidth(100);

                    presionarCarta(arBtncartas[i][j], reversoView, frenteView);

                    gdpImagenes.add(arBtncartas[i][j], j, i);
                    pos++;
                }
            }
        }
    }

    private void presionarCarta(Button btnCarta, ImageView reversoView, ImageView frenteView) {
        btnCarta.setOnAction(e -> {
            if (!esperandoVolteo) {
                // Verificar si la carta ya está volteada
                if (btnCarta.getGraphic() == frenteView) {
                    return; // Salir del método si la carta ya está volteada
                }

                // Voltear la carta
                btnCarta.setGraphic(frenteView);

                if (primeraCarta == null) {
                    primeraCarta = btnCarta;
                } else if (segundaCarta == null) {
                    segundaCarta = btnCarta;
                    verificarPareja();
                }
            }
        });
    }

    private void verificarPareja() {
        esperandoVolteo = true;

        // Obtener las imágenes de las cartas
        ImageView imagenPrimeraCarta = (ImageView) primeraCarta.getGraphic();
        ImageView imagenSegundaCarta = (ImageView) segundaCarta.getGraphic();

        if (imagenPrimeraCarta.getImage().getUrl().equals(imagenSegundaCarta.getImage().getUrl())) {
            // Las imágenes son iguales
            Platform.runLater(() -> {
                if (turnoActual == 1) {
                    paresJugador1++;
                    lbl3.setText(String.valueOf(paresJugador1));
                } else {
                    paresJugador2++;
                    lbl4.setText(String.valueOf(paresJugador2));
                }
                resetTurno();
                esperandoVolteo = false;
            });
        } else {
            // Las imágenes no son iguales
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                // Voltear las cartas de nuevo
                Platform.runLater(() -> {
                    primeraCarta.setGraphic(new ImageView(getClass().getResource(reversoPath).toString()));
                    segundaCarta.setGraphic(new ImageView(getClass().getResource(reversoPath).toString()));
                    cambiarTurno();
                    esperandoVolteo = false;
                });
            }).start();
        }
    }


    private void cambiarTurno() {
        if (turnoActual == 1) {
            turnoActual = 2;
            hContenedor3.setStyle("-fx-background-color: red;");
            hContenedor4.setStyle("-fx-background-color: green;");
        } else {
            turnoActual = 1;
            hContenedor3.setStyle("-fx-background-color: green;");
            hContenedor4.setStyle("-fx-background-color: red;");
        }
        resetTurno();
    }

    private void resetTurno() {
        primeraCarta = null;
        segundaCarta = null;
    }

    private void resetGame() {
        turnoActual = 1;
        paresJugador1 = 0;
        paresJugador2 = 0;
        lbl3.setText("0");
        lbl4.setText("0");

        resetTurno();
    }

}
