package com.example.carouselexperimentone;

import com.example.carouselexperimentone.carouselModel.CarouselTab;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private Path carouselPath;
    private List<CarouselTab> tabs;

    public Controller(Path path){
        this.carouselPath = path;
        this.tabs = getCarouselTabs();
    }

    public List<CarouselTab> getCarouselTabs(){
        try(Stream<Path> stream = Files.walk(carouselPath,1)){
            return stream
                    .filter((Files::isDirectory))
                    .filter(path -> (!path.equals(carouselPath)))
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
