package mx.unam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class controller {

    @FXML
    private Button botonBuscar;

    @FXML
    private Button botonValidar;

    @FXML
    private TextArea txtMostrar;

    @FXML
    private TextArea txtResultado;

    @FXML
    void BuscarArchivo(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f != null) {
            try{
                BufferedReader br = new BufferedReader(new FileReader(f));
                String linea;
                txtMostrar.clear();
                while ((linea = br.readLine()) != null) {
                    txtMostrar.appendText(linea + "\n");
                }
                br.close();
            } catch (IOException e) {
                txtMostrar.setText("Error al leer el archivo");
            }
        }
    }

    @FXML
    void validarTexto(ActionEvent event) {
        txtResultado.clear();

        String texto = txtMostrar.getText();
        String[] textos = texto.split("\\n");

        for (String texto1 : textos) {
            if (texto1.trim().isEmpty()) {
                continue;
            }
            if (validar(texto1)) {
                txtResultado.appendText(texto1 + " --> ACEPTADA \n");
            }else{
                txtResultado.appendText(texto1 + " --> RECHAZADA \n");
            }

        }
    }

    private boolean validar(String cadena) {
        for (char c: cadena.toCharArray()) {
            if ( c != 'a' && c != 'b') {
                return false;
            }
        }
        enum Estado {
            q1, q2, q3, q4
        }
        Estado estadoActual = Estado.q1;

        for (char simbolo : cadena.toCharArray()) {
            switch (estadoActual) {

                case q1:
                    if (simbolo == 'a') estadoActual = Estado.q2;
                    else if (simbolo == 'b') estadoActual = Estado.q3;
                    break;

                case q2:
                    if (simbolo == 'a') estadoActual = Estado.q4;
                    else if (simbolo == 'b') estadoActual = Estado.q3;
                    break;

                case q3:
                    if (simbolo == 'a') estadoActual = Estado.q2;
                    else if (simbolo == 'b') estadoActual = Estado.q3;
                    break;

                case q4:
                    // Estado absorbente
                    if (simbolo == 'a' || simbolo == 'b')
                        estadoActual = Estado.q4;
                    break;
            }
        }

        return estadoActual == Estado.q4;
    }
}