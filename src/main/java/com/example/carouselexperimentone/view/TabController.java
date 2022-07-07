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
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TabController {
    private final Controller controller;
    private final CarouselTab carouselTab;
    @FXML
    MenuButton menuButton;
    @FXML
    Button leftButton;
    @FXML
    Button rightButton;
    @FXML
    private VBox tabRootVBox;
    @FXML
    private Label documentName;
    private ImageView imageView;

    public TabController(CarouselTab carouselTab, Controller controller) {
        this.carouselTab = carouselTab;
        this.controller = controller;
    }

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

    @FXML
    private void deleteDocument(){
        int index = getCurrentImageIndex();
        Image image = null;
        if( !(carouselTab.getFileList().isEmpty())
        && index > -1) {
            carouselTab.deleteFile(
                    carouselTab.getFileList()
                            .get(index));
        }
        // reduce index to prevent outOfBoundsError
        if(index >= carouselTab.getFileList().size()) --index;
        // show image if index is valid
        if(index >= 0) imageView.setImage(new Image(carouselTab.getFileList().get(index).toString()));
        // show no_document if FileList is empty
        if(carouselTab.getFileList().isEmpty()){
            try {
                imageView.setImage( new Image( Paths.get(getClass().getResource("/com/example/carouselexperimentone/no_document.png").toURI()).toString()) );
            } catch (URISyntaxException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private ImageViewPane createImageViewPane() {
        Image image = null;
        if(!(carouselTab.getFileList().isEmpty())){
            // show first Image if available
            image = new Image( carouselTab.getFileList().get(0).toString() );
        }
        else {
            try {
                image = new Image( Paths.get(getClass().getResource("/com/example/carouselexperimentone/no_document.png").toURI()).toString());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            }


        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        ImageViewPane iVP =  new ImageViewPane(imageView);
        VBox.setVgrow(iVP, Priority.ALWAYS);
        return iVP;
    }

    private int getCurrentImageIndex(){
        try {
            return carouselTab.getFileList().indexOf(
                    Path.of(imageView.getImage().getUrl()));
        }
        catch(Exception e){
            return -1;
        }
    }

    private void updateDocumentFileName(Image image){
        if(image == null){
            documentName.setText("");
            return;
        }
        documentName.setText(
                Path.of(image.getUrl())
                        .getFileName()
                        .toString()
                        .split("\\.")[0]

        );
    }
}
