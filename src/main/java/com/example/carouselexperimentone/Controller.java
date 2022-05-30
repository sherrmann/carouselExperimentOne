package com.example.carouselexperimentone;

import com.example.carouselexperimentone.carouselModel.Carousel;
import com.example.carouselexperimentone.carouselModel.CarouselTab;
import com.example.carouselexperimentone.view.ImageViewPane;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Controller {
    private Path path = Paths.get("C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1\\");
    private Carousel carousel;
    @FXML
    VBox defaultVBox;
    @FXML
    Tab defaultTab;
    @FXML
    TabPane tabPane;
    ImageViewPane imageViewPane;
    ImageView imageView;

    public void initialize() {
        // TODO only for testing purposes
        carousel.scanDirectoryForTabs().get(0).getTabPath();
        carousel.scanDirectoryForTabs().get(0).getFileList().get(0).toString();
        Image image = new Image(carousel.scanDirectoryForTabs().get(0).getFileList().get(0).toString());
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        imageViewPane = new ImageViewPane(imageView);
        VBox.setVgrow(imageViewPane, Priority.ALWAYS);
        defaultVBox.getChildren().add(0, imageViewPane);
        tabPane.getTabs().addAll(createTabs());
        System.out.println(imageView.getImage().getUrl());

    }

    // creates a List of Tabs
    public List<Tab> createTabs(){
        List<String> tabs = carousel.getTabs().stream().map(CarouselTab::getTabName).toList();
        return carousel
                .getTabs()
                .stream()
                .map(CarouselTab::getTabName)
                .map(t -> new Tab(t))
                .toList();
    }

    public List<ImageView> createImages(){
        return carousel.getTabs().get(0).getFileList().stream()
                .map(p -> new ImageView(p.toString()))
                .toList();
    }

    public void setLeftButton(Event event) {
        Image image = new Image(getClass().getResource("java-cheatsheet.jpg").toString());
        imageView.setImage(image);
    }

    public void setRightButton(Event event) {
        Image image = new Image(getClass().getResource("intellij.png").toString());
        imageView.setImage(image);
    }

    public Controller() {
        this.carousel = new Carousel(path);
    }
}