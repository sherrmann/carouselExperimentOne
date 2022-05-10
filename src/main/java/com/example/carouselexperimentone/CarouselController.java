package com.example.carouselexperimentone;

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

    public void initialize(){
            url = getClass().getResource("CollectionsCheatSheet.png");
            String urlString = (url.toString() == null) ? "null" : url.toString();
            Image image = new Image( (url.toString() == null) ? "null" : url.toString() );
            imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageViewPane = new ImageViewPane(imageView);
            VBox.setVgrow(imageViewPane, Priority.ALWAYS);
            leftButton = new Button("left");
            rightButton = new Button("right");
            addMenuItem = new MenuItem("add Image");
            changeMenuItem = new MenuItem("change Image");
            deleteMenuItem = new MenuItem("delete Image");
            menuButton = new MenuButton("Menu", null, addMenuItem, changeMenuItem, deleteMenuItem);
            menuHBox = new HBox(menuButton, leftButton, rightButton);
            menuHBox.setSpacing(10);
            defaultVBox.getChildren().addAll(imageViewPane, menuHBox);
            tabPane.setSide(Side.LEFT);
    }

}