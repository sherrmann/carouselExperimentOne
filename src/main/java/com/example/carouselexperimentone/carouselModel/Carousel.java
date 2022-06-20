package com.example.carouselexperimentone.carouselModel;

import lombok.Data;
import lombok.NonNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Carousel {
    private List<CarouselTab> tabs;
    @NonNull
    private Path carouselPath;

    public Carousel(Path path){
        this.carouselPath = path;
        this.tabs = scanDirectoryForTabs();
        tabs.sort(Comparator.comparing(CarouselTab::getTabName));
    }

    // Collect folders from directory
    public List<CarouselTab> scanDirectoryForTabs() {
        // try-with-resources: scan path for folders
        try(Stream<Path> stream = Files.walk(carouselPath,1)){
            return stream
                    .filter((Files::isDirectory))
                    .filter(path -> (!path.equals(carouselPath))) // remove first directory which equals carouselPath
                    // create new CarouselTabs with name and abs path as argument
                    .map(path -> new CarouselTab(path.getFileName().toString(), path))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
