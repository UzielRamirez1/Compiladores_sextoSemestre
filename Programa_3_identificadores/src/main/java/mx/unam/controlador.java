package mx.unam;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mx.unam.modelo.modelo;

import java.io.*;

public class controlador {

    @FXML private TextFlow areaArchivo;
    @FXML private TextFlow areaResultado;

    private modelo modelo = new modelo();

    @FXML
    public void abrirArchivo() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt")
        );

        File archivo = fileChooser.showOpenDialog(new Stage());

        if (archivo != null) {
            procesarArchivo(archivo);
        }
    }

    private void procesarArchivo(File archivo) {

        areaArchivo.getChildren().clear();
        areaResultado.getChildren().clear();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;
            int numeroLinea = 1;

            while ((linea = br.readLine()) != null) {

                // Mostrar contenido
                Text textoArchivo = new Text(linea + "\n");
                areaArchivo.getChildren().add(textoArchivo);

                modelo.ResultadoValidacion resultado =
                        modelo.validarIdentificador(linea);

                Text textoResultado = new Text(
                        "Línea " + numeroLinea + ": " + linea + " → "
                                + (resultado.esValido ? "VÁLIDO\n" : "INVÁLIDO\n")
                                + (resultado.esValido ? "" : "Motivo: " + resultado.motivo + "\n")
                                + "\n"
                );

                textoResultado.setFill(
                        resultado.esValido ? Color.GREEN : Color.RED
                );

                areaResultado.getChildren().add(textoResultado);

                numeroLinea++;
            }

        } catch (IOException e) {
            Text error = new Text("Error al leer el archivo.");
            error.setFill(Color.RED);
            areaResultado.getChildren().add(error);
        }
    }
}
