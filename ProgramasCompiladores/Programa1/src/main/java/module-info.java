module fes.aragon.programa1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens fes.aragon.programa1 to javafx.fxml;
    exports fes.aragon.programa1;
}