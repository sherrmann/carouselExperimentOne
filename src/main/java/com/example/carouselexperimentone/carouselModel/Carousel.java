package com.example.carouselexperimentone.carouselModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Carousel {
    private List<CarouselTab> tabs;
    private Path carouselPath;

    public Carousel(Path path){
        this.carouselPath = path;
        this.tabs = getCarouselTabs();
    }

    public List<CarouselTab> getCarouselTabs(){
        // try with resources, list directories and collect List of tabs
        try(Stream<Path> stream = Files.walk(carouselPath,1)){
            return stream
                    .filter((Files::isDirectory))
                    .filter(path -> (!path.equals(carouselPath))) // remove first directory which equals carouselPath
                    .map(path -> new CarouselTab(path.getFileName().toString(), path))
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Path getCarouselPath() {
        return this.carouselPath;
    }

    public List<CarouselTab> getTabs() {
        return tabs;
    }

    public void setTabs(List<CarouselTab> tabs) {
        this.tabs = tabs;
    }
}
