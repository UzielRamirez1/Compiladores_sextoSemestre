module mx.unam.programa_6_compi {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.unam to javafx.fxml;
    exports mx.unam.controller;
    opens mx.unam.controller to javafx.fxml;
    exports mx.unam;
}