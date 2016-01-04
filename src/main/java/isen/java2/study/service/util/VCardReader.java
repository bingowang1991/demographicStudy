package isen.java2.study.service.util;

import isen.java2.study.data.Person;
import isen.java2.study.data.Sex;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VCardReader {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static Person read(Path path) {
        Person person = new Person();
        try {
            List<String> lines = getAllLines(path);
            for (String line : lines) {
                person = parseN(person, line);
                person = parseEmail(person, line);
                person = parseAddress(person, line);
                person = parseDateOfBirth(person, line);
                person = parseBloodType(person, line);
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error while reading VCard content");
        }
        return person;

    }

    private static List<String> getAllLines(Path path) throws IOException {
        BufferedReader fileReader = Files.newBufferedReader(path);
        List<String> result = new ArrayList<>();
        String line;

        while ((line = fileReader.readLine()) != null) {
            result.add(line);
        }

        return result;
    }

    private static Person parseN(Person person, String line) {
        if (line.substring(0, 2).equals("N:")) {

            String lineLeft = line.substring(2);

            String[] split = lineLeft.split(";");

            person.setLastName(split[0]);
            person.setFirstName(split[1]);

            if (split[3].equals("Mr")) {
                person.setSex(Sex.MALE);
            } else {
                person.setSex(Sex.FEMALE);
            }
        }
        return person;
    }

    private static Person parseEmail(Person person, String line) {
        if (line.substring(0, 6).equals("EMAIL:")) {
            String lineLeft = line.substring(6);
            person.setEmail(lineLeft);
        }
        return person;
    }

    private static Person parseAddress(Person person, String line) {
        // TODO if line begins with "ADR:", then split the line with ";"
        // separator
        // TODO the third element is the streetName
        // TODO the fourth element is the city
        // TODO the fifth element is the state
        // TODO return the person you got as parameter, with the new values
        // inside
        if (line.substring(0, 4).equals("ADR:")) {

            String lineLeft = line.substring(4);

            String[] split = lineLeft.split(";");

            // TODO use the splat values
        }
        return person;
    }

    private static Person parseDateOfBirth(Person person, String line) throws ParseException {
        // TODO if line begins with "BDAY:", parse the date thanks to
        // LocalDate.parse() and the given formatter
        // TODO return the person you got as parameter, with the new values
        // inside
        return person;
    }

    private static Person parseBloodType(Person person, String line) {
        // TODO if line begins with "CATEGORIES:", apply the correct substring
        // to retrieve the email
        // TODO return the person you got as parameter, with the new values
        // inside
        return person;
    }

}
