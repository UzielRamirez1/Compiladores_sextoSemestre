module mx.unam.programa_6_notacien {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.unam to javafx.fxml;
    exports mx.unam;
    exports mx.unam.controlador;
    opens mx.unam.controlador to javafx.fxml;
}