package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;

public class TabController {
    private Controller controller;
    public CarouselTab carouselTab;
    @FXML
    private VBox tabRootVBox;
    private ImageView imageView;

    public void initialize(){
        tabRootVBox.getChildren().add(0, makeImageViewPane());
    }
    @FXML
    private void leftButton(){
        int i = getCurrentImageIndex();
        if(i > 0){
            imageView.setImage(  new Image(  carouselTab.getFileList().get(--i).toString()));
        }
        System.out.println("left");
    }
    @FXML
    private void rightButton(){
        int i = getCurrentImageIndex();
        if(i < carouselTab.getFileList().size() - 1 ){
            imageView.setImage(  new Image(  carouselTab.getFileList().get(++i).toString()));
        }
        System.out.println("right");
    }
    @FXML
    private void refreshCarousel(){ controller.refreshCarousel(); }
    @FXML
    private void addDocument(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add a document");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(tabRootVBox.getScene().getWindow());
        carouselTab.addFile(file.toPath());
    }

    private ImageViewPane makeImageViewPane(){
        imageView = new ImageView(carouselTab.getFileList().get(0).toString());
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        ImageViewPane iVP =  new ImageViewPane(imageView);
        VBox.setVgrow(iVP, Priority.ALWAYS);
        return iVP;
    }

    private int getCurrentImageIndex(){
        return carouselTab.getFileList().indexOf(
                Path.of(imageView.getImage().getUrl()));
    }

    public TabController(CarouselTab carouselTab, Controller controller) {
        this.carouselTab = carouselTab;
        this.controller = controller;
    }
}
