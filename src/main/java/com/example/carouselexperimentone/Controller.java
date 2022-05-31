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
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private Path path = Paths.get("C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1\\");
    public Carousel carousel;
    private Map<Tab, List<Image>> tabs;
    @FXML
    VBox defaultVBox;
    @FXML
    Tab defaultTab;
    @FXML
    TabPane tabPane;
    ImageView imageView;

    public void initialize() {
        // TODO only for testing purposes
//        carousel.scanDirectoryForTabs().get(0).getTabPath();
//        carousel.scanDirectoryForTabs().get(0).getFileList().get(0).toString();
//        Image image = new Image(carousel.scanDirectoryForTabs().get(0).getFileList().get(0).toString());
//        imageView = new ImageView(image);
//        imageView.setPreserveRatio(true);
//        imageView.setSmooth(true);
//
//        imageViewPane = new ImageViewPane(imageView);
//        VBox.setVgrow(imageViewPane, Priority.ALWAYS);
//        defaultVBox.getChildren().add(0, imageViewPane);
//        tabPane.getTabs().addAll(createTabs());
//        System.out.println(imageView.getImage().getUrl());
        tabs = createTabs();
        tabPane.getTabs().addAll(tabs.keySet());

    }



    // creates a List of Tabs
//    public List<Tab> createTabs(){
//        return carousel
//                .getTabs()
//                .stream()
//                .map(CarouselTab::getTabName)
//                .map(t -> new Tab(t))
//                .map(t -> {
//                    t.setContent(createImages().get(0));
//                    return t;
//                })
//                .toList();
//    }
    private List<ImageView> getImageViewsFromTabPane(TabPane tP){
        return tP.getTabs().parallelStream()
                .map(t -> (ImageViewPane) t.getContent())
                .map(i -> i.getImageView())
                .toList();
    }
    private Map<Tab, List<Image>> createTabs(){
        return carousel.getTabs()
                .stream()
                .collect(Collectors.toMap(t -> createTabWithImageViewPane(t), t -> createImages(t)));
    }
    private List<Image> createImages(CarouselTab t){
        return t.getFileList().stream()
                .map(p -> new Image(p.toString()))
                .toList();
    }
    private Tab createTabWithImageViewPane(CarouselTab cTab){
        var tab = new Tab(cTab.getTabName());
        var iV = new ImageViewPane(new ImageView());
        ImageView img = new ImageView(cTab.getFileList().get(0).toString());
        img.setPreserveRatio(true);
        iV.setImageView(img);
        tab.setContent(iV);
        VBox.setVgrow(iV, Priority.ALWAYS);
        return tab;
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