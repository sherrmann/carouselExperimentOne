package com.example.carouselexperimentone;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;

public class CarouselController {
    AnchorPane anchorPane;
    ImageView imageView;
    Image image;
    URL url;
    public Parent createContent() {
        try {
            url = getClass().getResource("CollectionsCheatSheet.png");
            image = new Image(String.valueOf(url));
            imageView = new ImageView(image);
            anchorPane = new AnchorPane(imageView);



//            Image imageTab1 = new Image(getClass().getResourceAsStream("CollectionsCheatSheet.png"));
//            imageViewTab1.setImage(imageTab1);
//            imageViewTab1.fitWidthProperty().bind(tabPane.heightProperty());
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }

        return anchorPane;
    }

}