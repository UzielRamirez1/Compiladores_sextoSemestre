package fes.aragon.demo1;
import fes.aragon.demo1.modelo.Lexico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class HelloController {

    @FXML
    private TextField inputField;

    @FXML
    private Label resultLabel;

    private Lexico lexico = new Lexico();

    @FXML
    protected void onSelectFileClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo TXT");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt")
        );

        Stage stage = (Stage) resultLabel.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        // 🔥 Aquí se analiza automáticamente
        if (file != null) {
            analizarArchivo(file);
        }
    }

    @FXML
    protected void onAnalyzeClick() {
        String texto = inputField.getText().trim();

        if (!texto.isEmpty()) {
            boolean valido = lexico.analizar(texto);
            resultLabel.setText(valido ? "Cadena válida" : "Cadena inválida");
        } else {
            resultLabel.setText("Ingresa una cadena en el campo de texto");
        }
    }

    private void analizarArchivo(File file) {
        StringBuilder resultado = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            int numLinea = 1;

            while ((linea = br.readLine()) != null) {
                boolean valido = lexico.analizar(linea);

                resultado.append("Línea ")
                        .append(numLinea)
                        .append(": ")
                        .append(linea)
                        .append(" -> ")
                        .append(valido ? "VÁLIDA" : "INVÁLIDA")
                        .append("\n");

                numLinea++;
            }

            resultLabel.setText(resultado.toString());

        } catch (Exception e) {
            resultLabel.setText("Error al leer archivo");
            e.printStackTrace();
        }
    }
}