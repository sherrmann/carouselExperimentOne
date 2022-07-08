package com.example.prototype.view;

import com.example.prototype.carouselModel.Carousel;
import com.example.prototype.carouselModel.CarouselTab;
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
    private final Carousel docCarousel;
    private LinkedHashMap<Tab, TabController> tabsAndControllers;
    @FXML
    VBox defaultVBox; // top level VBox
    @FXML
    TabPane tabPane;

    public void initialize() {
        tabsAndControllers = createListOfTabs(docCarousel);
        tabPane.getTabs().addAll(tabsAndControllers.keySet());
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
            if (event.getCode() == KeyCode.DELETE){
                tabsAndControllers.get(tabPane.getSelectionModel().getSelectedItem()).deleteDocument();
                event.consume();
            };
        });
    }

    public void refreshCarousel() {
        tabPane.getTabs().removeAll(tabsAndControllers.keySet());
        tabsAndControllers = createListOfTabs(docCarousel);
        tabPane.getTabs().addAll(tabsAndControllers.keySet());
    }

    // Returns a Tab with a VBox, loads FXML, adds a TabController with carouselTab reference

    /**
     * @param carouselTab Content of the Tab
     * @return A JavaFX Tab wrapped in an Entry
     */
    private AbstractMap.SimpleEntry<Tab, TabController> createTab(CarouselTab carouselTab) {
        var tab = new Tab(carouselTab.getTabName());
        var tabController = new TabController(carouselTab, this);
        VBox vBox = new VBox();
        try {
            var loader = new FXMLLoader(getClass().getResource("/com/example/prototype/tab.fxml"));
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
        this.docCarousel = new Carousel(path);
    }
}