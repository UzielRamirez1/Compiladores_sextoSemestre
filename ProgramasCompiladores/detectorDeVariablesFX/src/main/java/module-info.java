module fes.aragon.detectordevariablesfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens fes.aragon.detectordevariablesfx to javafx.fxml;
    exports fes.aragon.detectordevariablesfx;
}