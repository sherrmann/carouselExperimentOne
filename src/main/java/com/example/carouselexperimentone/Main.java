package com.example.carouselexperimentone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    public void start(Stage stage) {
        Image image = new Image(getClass().getResourceAsStream("CollectionsCheatSheet.png"));
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        ImageViewPane imageViewPane = new ImageViewPane(imageView);
        Button button = new Button("left");
        Button button2 = new Button("change Image");
        Button button3 = new Button("add Image");
        Button button4 = new Button("right");
//        button.set
        HBox bottomMenu = new HBox(button, button2, button3, button4);
        VBox vBox = new VBox(imageViewPane, bottomMenu);
        VBox.setVgrow(vBox, Priority.ALWAYS);
        VBox.setVgrow(imageViewPane, Priority.ALWAYS);
        vBox.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        bottomMenu.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        Tab tab1 = new Tab("Java", vBox);
        vBox.setFillWidth(true);
        TabPane tabPane = new TabPane(tab1);
        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}