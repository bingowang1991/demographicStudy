package isen.java2.study.data.stat;

import isen.java2.study.Application;
import isen.java2.study.service.DBService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by plesur on 1/5/16.
 */
public class StatsTestCase {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    Properties properties;
    DBService dbService;

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        properties = Application.loadProperties();
        dbService = new DBService(properties);
    }

    @Test
    public void shouldPrintAverageAge() {
        // GIVEN
        AverageAgeByState averageAgeByState = new AverageAgeByState();

        // WHEN
        dbService.executeStat(averageAgeByState);

        // THEN
        assertThat(outContent.toString()).containsSequence("State: Idaho\n" +
                "Average age: 57");

        outContent.reset();
    }

    @Test
    public void shouldPrintMostCommonBloodType() {
        // GIVEN
        MostCommonBloodType mostCommonBloodType = new MostCommonBloodType();

        // WHEN
        dbService.executeStat(mostCommonBloodType);

        // THEN
        assertThat(outContent.toString()).containsSequence("Blood type: O+");

        outContent.reset();
    }

    @Test
    public void shouldGetMostCommonNames() {
        for (int i = 0; i < 8; i++) {
            // GIVEN
            CommonLastNamesByState commonLastNamesByState = new CommonLastNamesByState(i);

            // WHEN
            dbService.executeStat(commonLastNamesByState);

            // THEN
            for (int j = 0; j < i + 1; j++) {
                assertThat(outContent.toString()).doesNotContain("Number of occurrences: " + j);
            }

            outContent.reset();
        }
    }

    @After
    public void cleanUpStreams() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
    }
}
