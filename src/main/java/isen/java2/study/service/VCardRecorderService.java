package isen.java2.study.service;

import isen.java2.study.service.util.FilesCrawler;
import isen.java2.study.service.util.VCardReader;

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

/**
 * Created by plesur on 1/4/16.
 */
public class VCardRecorderService {
    private DBService dbService;
    private Properties properties;

    public VCardRecorderService(DBService dbService, Properties properties) {
        this.dbService = dbService;
        this.properties = properties;
    }

    public void readAndSaveCards() {
        List<Path> paths = FilesCrawler.getFiles(properties.getProperty("vcards.folder"));

        if (paths == null) {
            return;
        }
        for (Path path : paths) {
            dbService.save(VCardReader.read(path));
        }

    }
}
