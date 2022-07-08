package com.example.prototype.carouselModel;

import lombok.Data;
import lombok.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private List<Path> fileList;

    public CarouselTab (String tabName, Path tabPath){
        this.tabName = tabName;
        this.tabPath = tabPath;
        this.fileList = getFilesInTabFolder(this.tabPath);
        if(!(fileList == null)) Collections.sort(fileList);
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

    public void addFile(Path path) {
        // TODO: save File in right directory
        try {
            Files.copy(path, Paths.get(tabPath + "\\" + path.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.fileList = getFilesInTabFolder(this.tabPath);
        if(!(fileList == null)) Collections.sort(fileList);
    }

    public void deleteFile(Path path) {
        try{
            Files.deleteIfExists(path);
            fileList.remove(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Path> getFileList() {
        return Collections.unmodifiableList(fileList);
    }

    public void setFileList(List<Path> fileList){
        this.fileList = new ArrayList<>(fileList);
    }
}
