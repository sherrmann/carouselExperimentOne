package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;

public class TabController {
    private final Controller controller;
    private final CarouselTab carouselTab;
    @FXML
    private VBox tabRootVBox;
    @FXML
    private Label documentName;
    @FXML
    MenuButton menuButton;
    @FXML
    Button leftButton;
    @FXML
    Button rightButton;

    private ImageView imageView;

    public void initialize(){
        tabRootVBox.getChildren().add(0, createImageViewPane()); // insert IVP at the first position
        imageView.imageProperty().addListener(((observable, oldImage, newImage) -> updateDocumentFileName(newImage)));
        updateDocumentFileName(imageView.getImage());
    }
    @FXML
    void leftButton(){
        int i = getCurrentImageIndex();
        if(i > 0){
            imageView.setImage(  new Image(  carouselTab.getFileList().get(--i).toString()));
        }
    }
    @FXML
    void rightButton(){
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
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images JPEG, GIF, PNG, BMP","BMP", "GIF", "JPEG", "JPG", "PNG"));
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

    private void updateDocumentFileName(Image image){
        documentName.setText(
                Path.of(image.getUrl())
                        .getFileName()
                        .toString()
                        .split("\\.")[0]

        );
    }

    public TabController(CarouselTab carouselTab, Controller controller) {
        this.carouselTab = carouselTab;
        this.controller = controller;
    }
}
