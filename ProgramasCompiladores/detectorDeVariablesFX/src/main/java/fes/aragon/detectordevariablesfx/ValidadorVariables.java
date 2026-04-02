package fes.aragon.detectordevariablesfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class ValidadorVariables extends Application {

    private TextFlow areaArchivo;
    private TextFlow areaResultado;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Button botonAbrir = new Button("Abrir archivo");

        areaArchivo = new TextFlow();
        areaResultado = new TextFlow();

        VBox columnaIzquierda = new VBox(10, botonAbrir, areaArchivo);
        VBox columnaDerecha = new VBox(10, areaResultado);

        HBox root = new HBox(20, columnaIzquierda, columnaDerecha);

        botonAbrir.setOnAction(e -> abrirArchivo(stage));

        Scene scene = new Scene(root, 900, 500);
        stage.setTitle("Validador de Identificadores");
        stage.setScene(scene);
        stage.show();
    }

    private void abrirArchivo(Stage stage) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt")
        );

        File archivo = fileChooser.showOpenDialog(stage);

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

                // Mostrar archivo tal cual
                Text textoArchivo = new Text(linea + "\n");
                areaArchivo.getChildren().add(textoArchivo);

                ResultadoValidacion resultado = validarIdentificador(linea);

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

    // Clase auxiliar para regresar resultado y motivo
    private static class ResultadoValidacion {
        boolean esValido;
        String motivo;

        ResultadoValidacion(boolean esValido, String motivo) {
            this.esValido = esValido;
            this.motivo = motivo;
        }
    }

    // AUTÓMATA CON EXPLICACIÓN
    private ResultadoValidacion validarIdentificador(String cadena) {

        if (cadena.isEmpty())
            return new ResultadoValidacion(false, "Identificador vacío");

        int estado = 1;

        for (int i = 0; i < cadena.length(); i++) {

            char simbolo = cadena.charAt(i);

            switch (estado) {

                case 1:
                    if (Character.isLetter(simbolo)) {
                        estado = 3;
                    } else if (Character.isDigit(simbolo)) {
                        return new ResultadoValidacion(false,
                                "No puede comenzar con número");
                    } else {
                        return new ResultadoValidacion(false,
                                "Debe comenzar con letra");
                    }
                    break;


                case 3:
                    if (Character.isLetter(simbolo) ||
                            Character.isDigit(simbolo) ||
                            simbolo == '_') {
                        estado = 3;
                    } else if (simbolo == ' ') {
                        return new ResultadoValidacion(false,
                                "No puede contener espacios");
                    } else {
                        return new ResultadoValidacion(false,
                                "Contiene carácter inválido: '" + simbolo + "'");
                    }
                    break;
            }
        }

        return new ResultadoValidacion(true, "");
    }
}
