package com.example.carouselexperimentone.carouselModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarouselTab {
    // variables
    private String tabName;
    private Path tabPath;
    private List<Path> fileList;

    public CarouselTab(String tabName, Path tabPath){
        this.tabName = tabName;
        this.tabPath = tabPath;
        this.fileList = getFilesInTabFolder(this.tabPath);
    }

    private List<Path> getFilesInTabFolder(Path dir){
        try (Stream<Path> stream = Files.walk(dir,1)){
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::toAbsolutePath)
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public Path getTabPath() {
        return tabPath;
    }

    public void setTabPath(Path tabPath) {
        this.tabPath = tabPath;
    }

    public List<Path> getFileList() {
        return fileList;
    }

    public void setFileList() {
        this.getFilesInTabFolder(tabPath);
    }
}
