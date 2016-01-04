package isen.java2.study.service.util;

import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by plesur on 1/4/16.
 */
public class FilesCrawlerTestCase {

    @Test
    public void shouldReturnNull() {
        String notExistingPath = "/home/plesur/Downloads/randomnamethatdoesnotexist normally";
        assertThat(FilesCrawler.getFiles(notExistingPath)).isNull();
    }

    @Test
    public void shouldNotReturnNull() {
        String existingPath = "/home/plesur/Downloads/vcards";
        assertThat(FilesCrawler.getFiles(existingPath)).isNotNull();
    }

    @Test
    public void shouldListVCards() {
        URL vcardURL = this.getClass().getClassLoader().getResource("directory");
        List<Path> result = FilesCrawler.getFiles(vcardURL.toString());
        assertThat(result).hasSize(2);
    }
}