package fes.aragon.ico.leertxt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader cargador = new FXMLLoader(
                getClass().getResource("/fes/aragon/ico/leertxt/hello-view.fxml")
        );

        Scene escena = new Scene(cargador.load(), 500, 400);

        stage.setTitle("Reconocer palabras con aa desde archivo");
        stage.setScene(escena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
