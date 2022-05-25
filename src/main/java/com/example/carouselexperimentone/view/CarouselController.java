package com.example.carouselexperimentone.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CarouselController {
    @FXML
    TabPane tabPane;
    @FXML
    Tab defaultTab;
    @FXML
    VBox defaultVBox;
    ImageViewPane imageViewPane;
    ImageView imageView;

    public void initialize() {
        // image
        Image image = new Image("file:src/main/resources/com/example/carouselexperimentone/documents/CollectionsCheatSheet.png");
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageViewPane = new ImageViewPane(imageView);
        VBox.setVgrow(imageViewPane, Priority.ALWAYS);
        defaultVBox.getChildren().add(0, imageViewPane);
        tabPane.setSide(Side.LEFT);


    }

    public void setLeftButton(Event event) {
        Image image = new Image(getClass().getResource("java-cheatsheet.jpg").toString());
        imageView.setImage(image);
    }

    public void setRightButton(Event event) {
        Image image = new Image(getClass().getResource("intellij.png").toString());
        imageView.setImage(image);
    }

}