package com.example.carouselexperimentone;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;

public class CarouselController {
    @FXML
    TabPane tabPane;
    @FXML
    Tab defaultTab;
    @FXML
    VBox defaultVBox;
    ImageViewPane imageViewPane;
    ImageView imageView;
    Image image;
    URL url;
    HBox menuHBox;
    MenuButton menuButton;
    MenuItem addMenuItem, changeMenuItem, deleteMenuItem;
    Button leftButton, rightButton;

    public void initialize() {
        // image
        url = getClass().getResource("CollectionsCheatSheet.png");
        Image image = new Image(url.toString());
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