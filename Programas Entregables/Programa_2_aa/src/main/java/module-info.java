module mx.unam.programa_2_aa {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.unam to javafx.fxml;
    exports mx.unam;
}