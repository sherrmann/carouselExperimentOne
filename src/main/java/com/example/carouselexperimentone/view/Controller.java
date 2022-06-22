package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.Carousel;
import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private Carousel carousel;
    private List<Tab> tabs;
    @FXML
    VBox defaultVBox; // top level VBox
    @FXML
    TabPane tabPane;

    public void initialize() {
        tabs = createTabs(carousel);
        tabPane.getTabs().addAll(tabs);
    }
    // Generates a Tab with a VBox, loads FXML, adds a TabController
    private Tab generateTab(CarouselTab carouselTab){
        var tab = new Tab(carouselTab.getTabName());
        VBox vBox = new VBox();
        VBox.setVgrow(vBox, Priority.ALWAYS);
        try {
            var loader = new FXMLLoader(getClass().getResource("/com/example/carouselexperimentone/tab.fxml"));
            loader.setController(new TabController(carouselTab));
            vBox = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tab.setContent(vBox);
        return tab;
    }

    private List<Tab> createTabs(Carousel carousel){
        return carousel.getTabs()
                .stream()
                .map(this::generateTab)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Controller() {
        Path path = Paths.get("C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1\\");
        this.carousel = new Carousel(path);
    }
}