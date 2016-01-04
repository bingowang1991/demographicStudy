package isen.java2.study;

import isen.java2.study.service.DBService;
import isen.java2.study.service.StatService;
import isen.java2.study.service.VCardRecorderService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {
        Properties properties = loadProperties();
        DBService dbService = new DBService(properties);
        VCardRecorderService vCardRecorderService = new VCardRecorderService(dbService, properties);
        StatService statService = new StatService(dbService);

        vCardRecorderService.readAndSaveCards();
    }

    private static Properties loadProperties() {
        System.out.println("Loading properties...");
        Properties properties = new Properties();
        InputStream fileStream = Application.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(fileStream);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier de propriétés");
        }
        return properties;
    }

}
