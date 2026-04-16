module mx.unam.programa_1_00 {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.unam to javafx.fxml;
    exports mx.unam;
}