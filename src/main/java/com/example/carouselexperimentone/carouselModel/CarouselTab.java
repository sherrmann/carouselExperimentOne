package com.example.carouselexperimentone.carouselModel;

import lombok.Data;
import lombok.NonNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class CarouselTab{
    @NonNull
    private String tabName;
    @NonNull
    private Path tabPath;
    @NonNull
    private List<Path> fileList;

    public CarouselTab (String tabName, Path tabPath){
        this.tabName = tabName;
        this.tabPath = tabPath;
        this.fileList = getFilesInTabFolder(this.tabPath);
        Collections.sort(fileList);
    }

    // scan tab directory for images
    private List<Path> getFilesInTabFolder(Path dir){
        try (Stream<Path> stream = Files.walk(dir,1)){
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toAbsolutePath)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
