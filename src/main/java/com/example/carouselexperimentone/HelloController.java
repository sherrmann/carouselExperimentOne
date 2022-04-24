package com.example.carouselexperimentone;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class HelloController{
    @FXML
    TabPane tabPane;
    @FXML
    Tab tab1;
    @FXML
    AnchorPane tab1Pane;
    @FXML
    ImageView imageViewTab1;
    @FXML
    Tab tab2;
    @FXML
    AnchorPane tab2Pane;
    @FXML
    ImageView imageViewTab2;

    @FXML
    public void initialize() {
        Image imageTab1 = new Image(getClass().getResourceAsStream("CollectionsCheatSheet.png"));
        imageViewTab1.fitWidthProperty().bind(tab1Pane.widthProperty());
        imageViewTab1.setImage(imageTab1);
    }

}