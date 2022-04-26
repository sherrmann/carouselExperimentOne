package com.example.carouselexperimentone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("CollectionsCheatSheet.png")));
        imageView.setPreserveRatio(true);
        AnchorPane anchorPane = new AnchorPane(imageView);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        imageView.fitHeightProperty().bind(scene.heightProperty());
        imageView.fitWidthProperty().bind(scene.widthProperty());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}