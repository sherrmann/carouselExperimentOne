package com.example.carouselexperimentone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("CollectionsCheatSheet.png")));
        imageView.setPreserveRatio(true);
        AnchorPane anchorPane = new AnchorPane(imageView);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        imageView.fitHeightProperty().bind(anchorPane.heightProperty()); // bind(scene... works as well
        imageView.fitWidthProperty().bind(anchorPane.widthProperty());  // bind(stage... cuts parts of the image off
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}