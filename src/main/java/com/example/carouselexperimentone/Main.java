package com.example.carouselexperimentone;

import com.example.carouselexperimentone.carouselModel.CarouselTab;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

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
        Parent parent = FXMLLoader.load(getClass().getResource("tabLayout.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        var tab = new CarouselTab("testTab", Paths.get("C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1\\Tab1"));
        System.out.println(tab.getFileList());
        launch();
    }
}