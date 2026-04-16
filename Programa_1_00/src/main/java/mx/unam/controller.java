package mx.unam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;

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
    private void validarTexto(ActionEvent event) {
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
        enum Estado {
            q0, q1, q2, q3, q4, q5, qd
        }
        for (char c: cadena.toCharArray()) {
            if ( c != '0' && c != '1') {
                return false;
            }
        }
        /*
        ESTE AFD ACEPTA CADENAS QUE EMPIEZEN CON 00 Y TERMINE CON 00, CON UNA
        LONGITD MINIMA DE 2
        */

        Estado estadoActual = Estado.q0;

        for (char simbolo : cadena.toCharArray()) {
            switch (estadoActual) {

                case q0:
                    estadoActual = (simbolo == '0') ? Estado.q1 : Estado.qd;
                    break;

                case q1:
                    estadoActual = (simbolo == '0') ? Estado.q2 : Estado.qd;
                    break;

                case q2:
                    if (simbolo == '0') estadoActual = Estado.q4;
                    else if (simbolo == '1') estadoActual = Estado.q3;
                    else estadoActual = Estado.qd;
                    break;

                case q3:
                    if (simbolo == '0') estadoActual = Estado.q4;
                    else if (simbolo == '1') estadoActual = Estado.q3;
                    else estadoActual = Estado.qd;
                    break;

                case q4:
                    if (simbolo == '0') estadoActual = Estado.q5;
                    else if (simbolo == '1') estadoActual = Estado.q3;
                    else estadoActual = Estado.qd;
                    break;

                case q5:
                    if (simbolo == '0') estadoActual = Estado.q5;
                    else if (simbolo == '1') estadoActual = Estado.q3;
                    else estadoActual = Estado.qd;
                    break;

                default:
                    estadoActual = Estado.qd;
            }
        }

        return estadoActual == Estado.q5 || estadoActual == Estado.q2;
    }
}
