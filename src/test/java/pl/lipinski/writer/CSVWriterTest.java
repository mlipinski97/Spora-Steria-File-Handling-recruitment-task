package pl.lipinski.writer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CSVWriterTest {

    //Object under the test
    CSVWriter csvWriter = CSVWriter.getInstance();

    @Test
    @DisplayName("when given file name and data it creates new csv file ")
    void whenGivenCorrectFileNameAndPreparedData_thenItCratesNewFile() throws IOException {
        //given
        Map<String, Long> testData = new HashMap<>();
        testData.put("supply", 10L);
        testData.put("buy", 5L);
        testData.put("result", 5L);
        final Path path = Path.of("./src/test/resources/reportTestData.csv");
        //then
        csvWriter.write(path.toString(), testData);
        assertTrue(Files.exists(path));
        Files.delete(path);
    }
}
