module com.example.carouselexperimentone {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires commons.vfs2;

    opens com.example.carouselexperimentone to javafx.fxml;
    exports com.example.carouselexperimentone;
    exports com.example.carouselexperimentone.view;
    opens com.example.carouselexperimentone.view to javafx.fxml;
}