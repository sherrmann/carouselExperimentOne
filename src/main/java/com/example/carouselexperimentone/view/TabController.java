package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.nio.file.Path;

public class TabController {
    public CarouselTab carouselTab;
    @FXML
    private VBox tabRootVBox;
    private ImageView imageView;

    public TabController(CarouselTab carouselTab) { this.carouselTab = carouselTab; }

    public void initialize(){
        tabRootVBox.getChildren().add(0, makeImageViewPane());
        System.out.println(getCurrentImageIndex());
    }

    private ImageViewPane makeImageViewPane(){
        imageView = new ImageView(carouselTab.getFileList().get(0).toString());
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        ImageViewPane iVP =  new ImageViewPane(imageView);
        VBox.setVgrow(iVP, Priority.ALWAYS);
        return iVP;
    }

    private int getCurrentImageIndex(){
        return carouselTab.getFileList().indexOf(
                Path.of(imageView.getImage().getUrl()));
    }

}
