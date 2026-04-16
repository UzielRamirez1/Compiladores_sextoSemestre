module mx.unam.programa_4_00ver3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.unam to javafx.fxml;
    exports mx.unam;
    exports mx.unam.controlador;
    opens mx.unam.controlador to javafx.fxml;
}