module fes.aragon.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens fes.aragon.demo1 to javafx.fxml;
    exports fes.aragon.demo1;
}