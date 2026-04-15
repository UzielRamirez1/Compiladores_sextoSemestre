package mx.unam.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mx.unam.modelo.modelo;

import java.io.*;

public class controller {

    @FXML private TextField campoEntrada;
    @FXML private TextArea areaContenido;
    @FXML private TextFlow areaResultado;

    private modelo modelo = new modelo();

    @FXML
    public void abrirArchivo() {
        FileChooser fc = new FileChooser();
        File archivo = fc.showOpenDialog(new Stage());

        if (archivo != null) {
            cargarContenido(archivo);
        }
    }

    private void cargarContenido(File archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            StringBuilder contenido = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

            areaContenido.setText(contenido.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void evaluarTodo() {

        areaResultado.getChildren().clear();

        // 🔹 Evaluar entrada manual
        String entrada = campoEntrada.getText().trim();
        if (!entrada.isEmpty()) {
            evaluarLinea("Manual", entrada);
        }

        // 🔹 Evaluar archivo
        String[] lineas = areaContenido.getText().split("\n");

        int numLinea = 1;
        for (String linea : lineas) {

            if (linea.trim().isEmpty()) {
                numLinea++;
                continue;
            }

            evaluarLinea("Línea " + numLinea, linea.trim());
            numLinea++;
        }
    }

    private void evaluarLinea(String etiqueta, String linea) {

        modelo.Resultado r = modelo.validar(linea);

        Text texto = new Text(
                etiqueta + ": " + linea + " → " + r.mensaje + "\n"
        );

        if (r.valida) {
            texto.setFill(Color.GREEN);
        } else {
            texto.setFill(Color.RED);
        }

        areaResultado.getChildren().add(texto);
    }
}

