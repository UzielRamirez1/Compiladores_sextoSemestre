package mx.unam.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mx.unam.modelo.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class controlador {
    @FXML
    private TextArea areaSalida;

    @FXML
    private Button botonCargar;

    private modelo modelo = new modelo();

    @FXML
    public void initialize() {
        botonCargar.setOnAction(e -> leerArchivo());
    }

    private void leerArchivo() {

        FileChooser selector = new FileChooser();
        selector.setTitle("Seleccionar archivo TXT");

        Stage ventana = (Stage) botonCargar.getScene().getWindow();
        File archivo = selector.showOpenDialog(ventana);

        if (archivo != null) {

            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {

                String linea;
                areaSalida.clear();

                while ((linea = lector.readLine()) != null) {
                    if (linea.trim().isEmpty()) {
                        continue;
                    }
                    if (modelo.esValido(linea)) {
                        areaSalida.appendText(linea + "  -->  VALIDO\n");
                    } else {
                        areaSalida.appendText(linea + "  -->  INVALIDO\n");
                    }
                }

            } catch (IOException e) {
                areaSalida.setText("Error al leer el archivo.");
            }
        }
    }
}