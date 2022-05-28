package com.example.carouselexperimentone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Controller {
    private static Path carouselPath = Path.of(
            "C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1");
    private Path carousel;
    private List<Path> tabs;

    public static Path getCarouselPath() {
        return carouselPath;
    }

    public static void test() throws IOException {
//        collect tabs and images
        try {
            Stream<Path> paths = Files.list(carouselPath);
            paths.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
