package aragon.lexicoautoconfigv3.controller;

import aragon.lexicoautoconfigv3.model.AutomataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class AutomataController {

    @FXML private Button btnCargar;
    @FXML private Button btnEvaluar;
    @FXML
    private TextField txtCadena;
    @FXML private Label lblArchivoStatus;
    @FXML private Label lblResultado;

    private AutomataModel modelo = new AutomataModel();

    @FXML
    private void manejarCarga() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Texto", "*.txt"));
        File archivo = fileChooser.showOpenDialog(null);

        if (archivo != null) {
            try {
                modelo.cargarConfiguracion(archivo);
                lblArchivoStatus.setText("Cargado: " + archivo.getName());
                lblResultado.setText("Configuración lista.");

                // Habilitar controles
                txtCadena.setDisable(false);
                btnEvaluar.setDisable(false);
            } catch (Exception ex) {
                lblResultado.setText("Error en archivo: " + ex.getMessage());
                lblResultado.setStyle("-fx-text-fill: red;");
            }
        }
    }


    @FXML
    private void manejarEvaluacion() {
        String input = txtCadena.getText();
        if (input == null || input.isEmpty()) return;

        boolean esValida = modelo.evaluar(input);

        if (esValida) {
            lblResultado.setText("¡CADENA VÁLIDA!");
            lblResultado.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
        } else {
            lblResultado.setText("CADENA INVÁLIDA O ERROR");
            lblResultado.setStyle("-fx-text-fill: #c0392b; -fx-font-weight: bold;");
        }
    }
}