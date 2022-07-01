package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.Carousel;
import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Controller {
    private Carousel carousel;
    private LinkedHashMap<Tab, TabController> tabsAndControllers;
    @FXML
    VBox defaultVBox; // top level VBox
    @FXML
    TabPane tabPane;

    public void initialize() {
        tabsAndControllers = createListOfTabs(carousel);
        tabPane.getTabs().addAll(tabsAndControllers.keySet());
        tabPane.setFocusTraversable(false);
        tabPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.LEFT) {
                tabsAndControllers.get(tabPane.getSelectionModel().getSelectedItem()).leftButton.fire();
                event.consume();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                tabsAndControllers.get(tabPane.getSelectionModel().getSelectedItem()).rightButton.fire();
                event.consume();
            }
            if (event.getCode() == KeyCode.UP) {
                int i = tabPane.getSelectionModel().getSelectedIndex();
                if(i > 0) tabPane.getSelectionModel().select(--i);
                event.consume();
            }

            if (event.getCode() == KeyCode.DOWN){
                int i = tabPane.getSelectionModel().getSelectedIndex();
                if(i < tabPane.getTabs().size() - 1) tabPane.getSelectionModel().select(i + 1);
                event.consume();
            }
        });

        // filter KeyEvents in vBox (alternative tabPane) and send event to selectedTab
//        defaultVBox.addEventFilter(KeyEvent.ANY, event -> {
//            System.out.println("inside EventFilter in Controller: " + event.getCode() );
//            tabs.get(tabPane.getSelectionModel().getSelectedIndex()).getContent().requestFocus();
//            event.consume();
//        });
//        tabPane.focusedProperty().addListener((observable, old, hasFocus) -> {
//            if(hasFocus) {
//                Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
//                VBox vBox = (VBox) selectedTab.getContent();
//                HBox hBox = (HBox) vBox.getChildren().get(1);
//                MenuButton menu = (MenuButton) hBox.getChildren().get(0);
//                menu.requestFocus();
//            }
//        });
    }

    public void refreshCarousel() {
        tabPane.getTabs().removeAll(tabsAndControllers.keySet());
        tabsAndControllers = createListOfTabs(carousel);
        tabPane.getTabs().addAll(tabsAndControllers.keySet());
    }

    // Generates a Tab with a VBox, loads FXML, adds a TabController with carouselTab reference
    private AbstractMap.SimpleEntry<Tab, TabController> createTab(CarouselTab carouselTab) {
        var tab = new Tab(carouselTab.getTabName());
        var tabController = new TabController(carouselTab, this);
        VBox vBox = new VBox();
        try {
            var loader = new FXMLLoader(getClass().getResource("/com/example/carouselexperimentone/tab.fxml"));
            loader.setController(tabController);
            vBox = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tab.setContent(vBox);
        return new AbstractMap.SimpleEntry<>(tab, tabController);
    }

    private LinkedHashMap<Tab, TabController> createListOfTabs(Carousel carousel) {
        return carousel.getTabs()
                .stream()
                .map(this::createTab)
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue,
                        (k, v) -> v, LinkedHashMap::new));
    }

    public Controller() {
        Path path = Paths.get("C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1\\");
        this.carousel = new Carousel(path);
    }
}