package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TabController {
    public CarouselTab carouselTab;
    @FXML
    private VBox tabRootVBox;
    private ImageView imageView;

    public TabController(CarouselTab carouselTab) { this.carouselTab = carouselTab; }

    public void initialize(){
    }

    private ImageViewPane makeImageViewPane(){
        ImageView imageView = new ImageView(carouselTab.getFileList().get(0).toString());
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return new ImageViewPane(imageView);
    }

}
