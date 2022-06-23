package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    @FXML
    private Label documentName;
    private ImageView imageView;

    public void initialize(){
        tabRootVBox.getChildren().add(0, createImageViewPane());
        imageView.imageProperty().addListener(((observable, oldImage, newImage) ->
                documentName.setText(
                        String.valueOf(
                                Path.of(newImage.getUrl())
                                .getFileName()))));
    }
    @FXML
    private void leftButton(){
        int i = getCurrentImageIndex();
        if(i > 0){
            imageView.setImage(  new Image(  carouselTab.getFileList().get(--i).toString()));
        }
    }
    @FXML
    private void rightButton(){
        int i = getCurrentImageIndex();
        if(i < carouselTab.getFileList().size() - 1 ){
            imageView.setImage(  new Image(  carouselTab.getFileList().get(++i).toString()));
        }
    }
    @FXML
    private void addDocument(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add a document");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(tabRootVBox.getScene().getWindow());
        if(file != null && file.exists()) {
            carouselTab.addFile(file.toPath());
            controller.refreshCarousel();
        }
    }

    private ImageViewPane createImageViewPane(){
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
