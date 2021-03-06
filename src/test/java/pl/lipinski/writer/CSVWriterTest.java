package pl.lipinski.writer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CSVWriterTest {

    //Object under the test
    CSVWriter csvWriter = CSVWriter.getInstance();

    private static final Logger logger = Logger.getLogger(CSVWriterTest.class.getName());

    @Test
    @DisplayName("when given file name and data it creates new csv file ")
    void whenGivenCorrectFileNameAndPreparedData_thenItCratesNewFile() {
        //given
        Map<String, Long> testData = new HashMap<>();
        testData.put("supply", 292L);
        testData.put("buy", 159L);
        testData.put("result", 133L);
        String reportPath = "./src/test/resources/reportTestData.csv";
        String validationReportPath = "./src/test/resources/validationReport.csv";
        //then
        csvWriter.write(reportPath, testData);
        assertTrue(Files.exists(Path.of(reportPath)));
        try {
            assertEquals(Files.readAllLines(Path.of(reportPath)), Files.readAllLines(Path.of(validationReportPath)));
        } catch (IOException ioException) {
            fail("IOException occurred, see more: " + ioException.getMessage());
        }
        try {
            Files.delete(Path.of(reportPath));
        } catch (IOException ioException) {
            logger.info("There was an error deleting test report. See more details: " + ioException.getMessage());
        }
    }
}
