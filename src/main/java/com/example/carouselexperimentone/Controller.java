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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private Path path = Paths.get("C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1\\");
    private Carousel carousel;
    private Map<Tab, List<Image>> tabs; // list of Tabs and Lists of Images
    private List<ImageView> imageViews; // list of imageViews
    @FXML
    VBox defaultVBox; // top level VBox
    @FXML
    TabPane tabPane;
    ImageView imageView; // currently used in left/rightButton

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
        tabPane.getTabs().addAll(tabs.keySet()); // add all tabs to tabPane
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

    // creates a tab containing an ImageViewPane containing an ImageView
    private Tab createTabWithImageViewPane(CarouselTab carouselTab){
        var tab = new Tab(carouselTab.getTabName());
        var imageViewPane = new ImageViewPane(new ImageView());
        // create ImageView and set to image at index 0
        ImageView imageView = new ImageView(carouselTab.getFileList().get(0).toString());
        imageView.setPreserveRatio(true);
        imageViews.add(imageView);
        imageViewPane.setImageView(imageView);
        tab.setContent(imageViewPane);
        VBox.setVgrow(imageViewPane, Priority.ALWAYS);
        return tab;
    }

    // gets all CarouselTabs, returns tabs with ImageViewPane,ImageView
    private Map<Tab, List<Image>> createTabs(){
        return carousel.getTabs()
                .stream()
                .collect(Collectors.toMap(t -> createTabWithImageViewPane(t), t -> createImages(t)));
    }
    // gets List<Path> from CarouselTab and returns List<Image>
    private List<Image> createImages(CarouselTab t){
        return t.getFileList().stream()
                .map(p -> new Image(p.toString()))
                .toList();
    }

    public void setLeftButton(Event event) {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        ImageViewPane currentImage = (ImageViewPane) selectedTab.getContent();
        Image image = currentImage.getImageView().getImage();
        List<Image> imageList = tabs.get(selectedTab);

    }

    public void setRightButton(Event event) {
        Image image = new Image(getClass().getResource("intellij.png").toString());
        imageView.setImage(image);
    }

    public Controller() {
        this.carousel = new Carousel(path);
        imageViews = new ArrayList<>();
    }
}