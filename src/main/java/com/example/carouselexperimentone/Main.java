package com.example.carouselexperimentone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/* Thank you, thank you, thank you!!!
 https://stackoverflow.com/questions/48804283/javafx-imageview-fits-container

 add this to FXML:
 <?import PATH.TO.CLASS.ImageViewPane?>

<ImageViewPane fx:id="imageViewPane">
  <imageView>
    <ImageView fx:id="imageView"
               pickOnBounds="true"
               preserveRatio="true">
    </ImageView>
  </imageView>
</ImageViewPane>


*/

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent = fxmlLoader.load(getClass().getResource("tabLayout.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
//        Image image = new Image(getClass().getResourceAsStream("CollectionsCheatSheet.png"));
//        ImageView imageView = new ImageView(image);
//        imageView.setPreserveRatio(true);
//        ImageViewPane imageViewPane = new ImageViewPane(imageView);
//        Button button = new Button("left");
//        Button button2 = new Button("change Image");
//        Button button3 = new Button("add Image");
//        Button button4 = new Button("right");
//        HBox bottomMenu = new HBox(button, button2, button3, button4);
//        VBox vBox = new VBox(imageViewPane, bottomMenu);
//        VBox.setVgrow(imageViewPane, Priority.ALWAYS);
//        Tab tab1 = new Tab("Java", vBox);
//        Tab tab2 = new Tab("Git", new AnchorPane());
//        Tab tab3 = new Tab("IntelliJ", new HBox());
//        TabPane tabPane = new TabPane(tab1, tab2, tab3);
//        tabPane.setSide(Side.LEFT);
//        Scene scene = new Scene(tabPane);
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}