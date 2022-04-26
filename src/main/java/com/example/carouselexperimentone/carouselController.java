package com.example.carouselexperimentone;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class carouselController {
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
    HBox tab1HBox;

    @FXML
    public void initialize() {
        try {
            Image imageTab1 = new Image(getClass().getResourceAsStream("CollectionsCheatSheet.png"));
            imageViewTab1.setImage(imageTab1);
            imageViewTab1.fitWidthProperty().bind(tab1HBox.heightProperty());
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }


    }

}