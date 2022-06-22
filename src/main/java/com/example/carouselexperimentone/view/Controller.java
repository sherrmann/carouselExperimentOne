package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.Carousel;
import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public void initialize() {
        tabs = createTabs();
        tabPane.getTabs().addAll(tabs.keySet());
    }

    private Tab createTabWithImageViewPane(CarouselTab carouselTab){
        var tab = new Tab(carouselTab.getTabName());
        ImageView imageView = new ImageView(carouselTab.getFileList().get(0).toString());
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        var imageViewPane = new ImageViewPane(imageView);
        VBox vBox = new VBox();
        TabController tabController;
        try {
            var loader = new FXMLLoader(getClass().getResource("/com/example/carouselexperimentone/tab.fxml"));
            loader.setController(new TabController(carouselTab));
            vBox = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        vBox.getChildren().add(0, imageViewPane);
        tab.setContent(vBox);
        VBox.setVgrow(imageViewPane, Priority.ALWAYS);
        return tab;
    }

    private Map<Tab, List<Image>> createTabs(){
        return carousel.getTabs()
                .stream()
                .collect(Collectors.toMap(this::createTabWithImageViewPane, this::createImages, (u,v) -> u, LinkedHashMap::new));
    }


    private List<Image> createImages(CarouselTab t){
        return t.getFileList().stream()
                .map(p -> new Image(p.toString()))
                .toList();
    }
    private CarouselTab getCarouselTabFromSelectedTab(){
        return carousel.getTabs().stream()
                .filter(t -> t.getTabName().equals(tabPane.getSelectionModel().getSelectedItem().getText()))
                .findFirst()
                .get();
    }
    private ImageView getImageViewFromSelectedTab(){
        VBox vBox =  (VBox) tabPane.getSelectionModel().getSelectedItem().getContent();
        ImageViewPane iVP =  (ImageViewPane) vBox.getChildren().get(0);
        return iVP.getImageView();
    }
    private Tab getSelectedTab(){ return tabPane.getSelectionModel().getSelectedItem(); }
//    public void setLeftButton(Event event) {
//        int i = getCarouselTabFromSelectedTab().getFileList().stream()
//                .map(Path::toString)
//                .toList()
//                .indexOf(getImageViewFromSelectedTab().getImage().getUrl());
//
//        getImageViewFromSelectedTab().setImage(
//                new Image(getCarouselTabFromSelectedTab().getFileList().get(--i).toString()));
//    }
//    public void setRightButton(Event event) {
//        // get the current index
//        int i = getCarouselTabFromSelectedTab().getFileList().stream()
//                .map(Path::toString)
//                .toList()
//                .indexOf(getImageViewFromSelectedTab().getImage().getUrl());
//
//        getImageViewFromSelectedTab().setImage(new Image(getCarouselTabFromSelectedTab().getFileList().get(++i).toString()));

    //    }
    public Controller() {
        Path path = Paths.get("C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1\\");
        this.carousel = new Carousel(path);
    }
}