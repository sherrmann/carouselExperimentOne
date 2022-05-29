package com.example.carouselexperimentone;

import com.example.carouselexperimentone.carouselModel.Carousel;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {
    private Path path = Paths.get("C:\\Users\\bubuf\\OneDrive - The Open University\\Documents\\DocumentCarousel\\Carousel1\\");
    private Carousel carousel;

    public Controller() {
        carousel = new Carousel(path);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Carousel getCarousel() {
        return carousel;
    }

    public void setCarousel(Carousel carousel) {
        this.carousel = carousel;
    }
}