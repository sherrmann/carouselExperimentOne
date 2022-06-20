package com.example.carouselexperimentone;

import com.example.carouselexperimentone.carouselModel.Carousel;
import com.example.carouselexperimentone.carouselModel.CarouselTab;
import com.example.carouselexperimentone.view.ImageViewPane;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private Carousel carousel;
    private Map<Tab, List<Image>> tabs; // list of Tabs and Lists of Images
    @FXML
    VBox defaultVBox; // top level VBox
    @FXML
    TabPane tabPane;
    @FXML
    Label indexLabel;

    public void initialize() {
        tabs = createTabs();
        tabPane.getTabs().addAll(tabs.keySet()); // add all tabs to tabPane
    }

    // creates a tab containing an ImageViewPane containing an ImageView
    private Tab createTabWithImageViewPane(CarouselTab carouselTab){
        var tab = new Tab(carouselTab.getTabName());
        // create ImageView and set to image at index 0
        ImageView imageView = new ImageView(carouselTab.getFileList().get(0).toString());
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        var imageViewPane = new ImageViewPane(imageView);
        VBox vBox = new VBox(imageViewPane,new HBox(new Button("Test")));
        tab.setContent(vBox);
        VBox.setVgrow(imageViewPane, Priority.ALWAYS);
        return tab;
    }

    // gets all CarouselTabs, returns tabs with ImageViewPane,ImageView
    private Map<Tab, List<Image>> createTabs(){
        return carousel.getTabs()
                .stream()
                .collect(Collectors.toMap(this::createTabWithImageViewPane, this::createImages, (u,v) -> u, LinkedHashMap::new));
    }
    // gets List<Path> from CarouselTab and returns List<Image>
    private List<Image> createImages(CarouselTab t){
        return t.getFileList().stream()
                .map(p -> new Image(p.toString()))
                .toList();
    }

    // get CarouselTab from selected Tab by comparing Names
    private CarouselTab getCarouselTabFromSelectedTab(){
        return carousel.getTabs().stream()
                .filter(t -> t.getTabName().equals(tabPane.getSelectionModel().getSelectedItem().getText()))
                .findFirst()
                .get();
    }

    // get ImageView from selected Tab
    private ImageView getImageViewFromSelectedTab(){
        ImageViewPane iVP =  (ImageViewPane) tabPane.getSelectionModel().getSelectedItem().getContent();
        return iVP.getImageView();
    }

    public void setLeftButton(Event event) {
        int i = getCarouselTabFromSelectedTab().getFileList().stream()
                .map(Path::toString)
                .toList()
                .indexOf(getImageViewFromSelectedTab().getImage().getUrl());

        getImageViewFromSelectedTab().setImage(
                new Image(getCarouselTabFromSelectedTab().getFileList().get(--i).toString()));
    }

    public void setRightButton(Event event) {
        // get the current index
        int i = getCarouselTabFromSelectedTab().getFileList().stream()
                .map(Path::toString)
                .toList()
                .indexOf(getImageViewFromSelectedTab().getImage().getUrl());

        getImageViewFromSelectedTab().setImage(new Image(getCarouselTabFromSelectedTab().getFileList().get(++i).toString()));
    }

    public Controller() {
        Path path = Paths.get("C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1\\");
        this.carousel = new Carousel(path);
    }
}