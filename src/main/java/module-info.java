module com.example.prototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;

    opens com.example.prototype to javafx.fxml;
    exports com.example.prototype;
    exports com.example.prototype.view;
    exports com.example.prototype.carouselModel;
    opens com.example.prototype.view to javafx.fxml;
}