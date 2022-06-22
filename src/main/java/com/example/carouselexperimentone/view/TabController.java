package com.example.carouselexperimentone.view;

import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.nio.file.Path;

public class TabController {
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

    public TabController(CarouselTab carouselTab) { this.carouselTab = carouselTab; }

    //    private List<Image> createImages(CarouselTab t){
//        return t.getFileList().stream()
//                .map(p -> new Image(p.toString()))
//                .toList();
//    }
//    private CarouselTab getCarouselTabFromSelectedTab(){
//        return carousel.getTabs().stream()
//                .filter(t -> t.getTabName().equals(tabPane.getSelectionModel().getSelectedItem().getText()))
//                .findFirst()
//                .get();
//    }
//    private ImageView getImageViewFromSelectedTab(){
//        VBox vBox =  (VBox) tabPane.getSelectionModel().getSelectedItem().getContent();
//        ImageViewPane iVP =  (ImageViewPane) vBox.getChildren().get(0);
//        return iVP.getImageView();
//    }
//    private Tab getSelectedTab(){ return tabPane.getSelectionModel().getSelectedItem(); }
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
}
