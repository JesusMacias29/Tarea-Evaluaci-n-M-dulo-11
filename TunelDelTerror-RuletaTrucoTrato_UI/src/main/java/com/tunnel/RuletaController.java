package com.tunnel;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RuletaController {

    @FXML private Label lblNombreCurso;
    @FXML private Label lblResultado;
    @FXML private Button btnGirar;
    @FXML private ImageView imgRuleta;
    @FXML private StackPane wheelContainer;

    private final Random random = new Random();
    private UserData userData;

    private record Segment(String tipo, String detalle) { }
    private final List<String> trucos = List.of(
            "Haz un grito de película de miedo.",
            "Habla en susurros durante 1 minuto",
            "Camina como zombie hasta la puerta",
            "Di “Buuuu” a alguien al azar sin avisar.",
            "Baila como un esqueleto.",
            "Imita a un fantasma por el pasillo"
    );
    private final List<String> tratos = List.of(
            "Regala una pegatina a un amigo",
            "Sincerate a tu profe",
            "Cede tu turno a alguien",
            "Ayuda a recoger el aula",
            "Haz un selfie de miedo y enséñalo.",
            "Sincerate a quien elijas."
    );
    private final List<Segment> sectores = new ArrayList<>();

    @FXML
    public void initialize() {
        // Alterna 8 sectores (4 TRUCO + 4 TRATO)
        for (int i = 0; i < 8; i++) {
            boolean esTruco = (i % 2 == 0);
            String tipo = esTruco ? "TRUCO" : "TRATO";
            String detalle = esTruco
                    ? trucos.get(random.nextInt(trucos.size()))
                    : tratos.get(random.nextInt(tratos.size()));
            sectores.add(new Segment(tipo, detalle));
        }

        imgRuleta.setCache(true);
        imgRuleta.setSmooth(true);
        imgRuleta.setEffect(new DropShadow(18, Color.color(0,0,0,0.55)));

        Polygon pointer = new Polygon(0.0,0.0, 16.0,0.0, 8.0,20.0);
        pointer.setFill(Color.web("#ffb300"));
        pointer.setStroke(Color.web("#4e2a00"));
        pointer.setStrokeWidth(1.5);
        pointer.setEffect(new DropShadow(8, Color.color(0,0,0,0.45)));
        StackPane.setAlignment(pointer, Pos.TOP_CENTER);
        pointer.setTranslateY(4);
        wheelContainer.getChildren().add(pointer);

        Circle hub = new Circle(10, Color.web("#ffffff"));
        hub.setStroke(Color.web("#222"));
        hub.setStrokeWidth(1.5);
        StackPane.setAlignment(hub, Pos.CENTER);
        wheelContainer.getChildren().add(hub);
    }

    public void setUserData(UserData data) {
        this.userData = data;
        if (data != null) {
            lblNombreCurso.setText(data.getNombreCompleto() + " · " + data.getCurso());
        }
    }

    @FXML
    private void onGirar() {
        btnGirar.setDisable(true);
        lblResultado.setText("");

        int ganador = random.nextInt(sectores.size());
        double anguloPorSector = 360.0 / sectores.size();
        double centroSector = ganador * anguloPorSector + anguloPorSector / 2.0;
        double vueltas = 4 + random.nextInt(2);
        double toAngle = - (vueltas * 360 + centroSector);

        RotateTransition rt = new RotateTransition(Duration.seconds(3.6), imgRuleta);
        rt.setFromAngle(0);
        rt.setToAngle(toAngle);
        rt.setInterpolator(Interpolator.SPLINE(0.11, 0.0, 0.15, 1.0));

        rt.setOnFinished(e -> {
            Segment s = sectores.get(ganador);
            String tipo = s.tipo();
            String detalle = s.detalle();

            lblResultado.setText(tipo + " — " + detalle);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(tipo + " para " + (userData != null ? userData.getNombreCompleto() : ""));
            alert.setContentText(detalle);
            alert.show();

            btnGirar.setDisable(false);
        });
        rt.play();
    }
}
