package com.example.carouselexperimentone;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    public void initialize() {
        try {
            url = getClass().getResource("CollectionsCheatSheet.png");
            image = new Image(url.toString());
            imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageViewPane = new ImageViewPane(imageView);
            VBox.setVgrow(imageViewPane, Priority.ALWAYS);
            menuHBox = new HBox(20.0, new Button("test"), new Button("anotherButton"));
            defaultVBox.getChildren().addAll(imageViewPane, menuHBox);
            tabPane.setSide(Side.LEFT);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}