package com.tunnel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EntradaController {

    @FXML private AnchorPane root;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private ComboBox<String> cmbCurso;

    @FXML
    public void initialize() {
        List<String> cursos = Arrays.asList("DAM1","DAM2","DAW1","DAW2","SMR1","SMR2");
        cmbCurso.getItems().addAll(cursos);
    }

    @FXML
    void onEntrar(ActionEvent event) {
        String nombre = txtNombre.getText() == null ? "" : txtNombre.getText().trim();
        String apellidos = txtApellidos.getText() == null ? "" : txtApellidos.getText().trim();
        String curso = cmbCurso.getValue();

        if (nombre.isEmpty() || apellidos.isEmpty() || curso == null || curso.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Falta información");
            alert.setContentText("Rellena Nombre, Apellidos y elige un Curso para entrar al túnel.");
            alert.showAndWait();
            return;
        }

        UserData data = new UserData(nombre, apellidos, curso);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ruleta.fxml"));
            Parent ruletaRoot = loader.load();
            RuletaController controller = loader.getController();
            controller.setUserData(data);

            Scene scene = new Scene(ruletaRoot, 900, 600);
            scene.getStylesheets().add(getClass().getResource("halloween.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Ruleta TRUCO / TRATO");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al cargar la ruleta");
            alert.setContentText("No se ha podido entrar al túnel. Intenta de nuevo.");
            alert.showAndWait();
        }
    }
}
