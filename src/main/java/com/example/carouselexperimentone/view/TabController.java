package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TabController {
    private CarouselTab carouselTab;
    @FXML
    private VBox tabRootVBox;

    public void initialize(){

    }
    public TabController(){
    }
    public void setCarouselTab(CarouselTab carouselTab){
        this.carouselTab = carouselTab;
    }
}
