module mx.unam.programa_3_identificadores {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.unam to javafx.fxml;
    exports mx.unam;
}