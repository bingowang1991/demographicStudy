package isen.java2.study.service.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by plesur on 1/4/16.
 */
public class FilesCrawler {
    public static List<Path> getFiles(String string) {
        Path vcardsPath = Paths.get(string);

        if (Files.notExists(vcardsPath)) {
            System.err.println("vcards path is not correct !");
            return null;
        }

        ArrayList<Path> vcardsArrayList = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(vcardsPath)) {
            for (Path path : stream) {
                vcardsArrayList.add(path);
            }
        } catch (IOException e) {
            System.err.println("Error creating the vcards arrayList");
            e.printStackTrace();
        }

        return vcardsArrayList;
    }
}
