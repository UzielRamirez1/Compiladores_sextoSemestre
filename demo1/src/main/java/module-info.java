module fes.aragon.demo1.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens fes.aragon.demo1 to javafx.fxml;
    exports fes.aragon.demo1;
}