package fes.aragon.demo1;

import fes.aragon.demo1.modelo.Lexico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField inputField;

    @FXML
    private Label resultLabel;

    private Lexico lexico = new Lexico();

    @FXML
    protected void onAnalyzeClick() {
        String cadena = inputField.getText();
        boolean valido = lexico.analizar(cadena);

        if (valido) {
            resultLabel.setText("Cadena válida");
        } else {
            resultLabel.setText("Cadena inválida");
        }
    }
}