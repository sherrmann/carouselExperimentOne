package com.example.carouselexperimentone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Controller {
    public static void test() throws IOException {
        String pathString = System.getProperty("user.home") + "\\Documents";
        Path path = Paths.get(pathString);
        try {
            Stream<Path> paths = Files.walk(path,1);
            paths.forEach(System.out::println);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
