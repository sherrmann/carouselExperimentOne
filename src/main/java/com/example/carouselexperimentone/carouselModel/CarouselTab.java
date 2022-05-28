package com.example.carouselexperimentone.carouselModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarouselTab {
    // variables
    public String tabName;
    public Set<String> fileList;

    public CarouselTab(String tabName){
        this.tabName = tabName;
    }

    public Set<Path> getFilesInTabFolder(Path dir) throws IOException {
        try (Stream<Path> stream = Files.walk(dir,99)){
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .collect(Collectors.toSet());
        }
    }
}
