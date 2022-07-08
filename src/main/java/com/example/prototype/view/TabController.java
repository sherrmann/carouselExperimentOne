package com.example.prototype.view;

import com.example.prototype.carouselModel.CarouselTab;
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
            imageView.setImage(getImageFromFileList(--i));
        }
    }

    @FXML
    void rightButton(){
        int i = getCurrentImageIndex();
        if(i < carouselTab.getFileList().size() - 1 ){
            imageView.setImage(getImageFromFileList(++i));
        }
    }

    @FXML
    private void addDocument(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add a document");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"));
        File file = fileChooser.showOpenDialog(tabRootVBox.getScene().getWindow());
        if(file != null && file.exists()) {
            carouselTab.addFile(file.toPath());
            controller.refreshCarousel();
        }
    }

    @FXML
    void deleteDocument(){
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

        imageView.setImage(getImageFromFileList(index));
    }

    /**
     *
     * @param index in carouselTab.FileList
     * @return Image at position in FileList, returns the no_document Image if FileList is empty or index out of bounds
     */
    private Image getImageFromFileList(int index){
        Image image = null;
        if(carouselTab.getFileList().isEmpty()){
            try {
                image = new Image(
                        Paths.get(
                                getClass().getResource("/com/example/prototype/no_document.png").
                                        toURI())
                                .toString());
            } catch (URISyntaxException | NullPointerException e) {
                e.printStackTrace();
            }
        } else if ( !(index < 0 | index > carouselTab.getFileList().size() - 1) ) {
            image = new Image(carouselTab.getFileList().get(index).toString());
        }
        return image;
    }

    private ImageViewPane createImageViewPane() {
        imageView = new ImageView();
        imageView.setImage(getImageFromFileList(0));
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
