package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.Controller;
import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FXController {
    // this variable introduces coupling!!!
    private Map<String, List<Path>> tabNameAndImagePaths;
    private Controller controller;
    @FXML
    TabPane tabPane;
    @FXML
    Tab defaultTab;
    @FXML
    VBox defaultVBox;
    ImageViewPane imageViewPane;
    ImageView imageView;

    public void initialize() {
        controller.getCarousel().getTabs().get(0).getTabPath();
        var v = controller.getCarousel().getTabs().get(0).getFileList().get(0).toString();
        Image image = new Image(controller.getCarousel().getTabs().get(0).getFileList().get(0).toString());
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageViewPane = new ImageViewPane(imageView);
        VBox.setVgrow(imageViewPane, Priority.ALWAYS);
        defaultVBox.getChildren().add(0, imageViewPane);


    }

    public void setLeftButton(Event event) {
        Image image = new Image(getClass().getResource("java-cheatsheet.jpg").toString());
        imageView.setImage(image);
    }

    public void setRightButton(Event event) {
        Image image = new Image(getClass().getResource("intellij.png").toString());
        imageView.setImage(image);
    }

    public Map<String, List<Path>> getTabNameAndImagePaths() {
        return tabNameAndImagePaths;
    }

    public FXController() {
        controller = new Controller();
        getTabNamesAndImagePaths();
    }

    private void getTabNamesAndImagePaths() {
        tabNameAndImagePaths = controller.getCarousel().getTabs().stream()
                .collect(Collectors.toMap(CarouselTab::getTabName,CarouselTab::getFileList));
    }

    public void setTabNameAndImagePaths(Map<String, List<Path>> tabNameAndImagePaths) {
        this.tabNameAndImagePaths = tabNameAndImagePaths;
    }
}