module aragon.lexicoautoconfigv3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens aragon.lexicoautoconfigv3.controller to javafx.fxml;






    opens aragon.lexicoautoconfigv3 to javafx.fxml;
    exports aragon.lexicoautoconfigv3;
}