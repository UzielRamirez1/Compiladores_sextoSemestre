package fes.aragon.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelloController {

    @FXML
    private Button btnElegir;

    @FXML
    private Button btnValidar;

    @FXML
    private TextArea txtMostrar;

    @FXML
    private TextArea txtValidar;

    @FXML
    void elegir(ActionEvent event) {
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
    void validar(ActionEvent event) {

    }
}
