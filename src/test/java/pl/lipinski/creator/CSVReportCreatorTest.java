package pl.lipinski.creator;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CSVReportCreatorTest {

    private static final Logger logger = Logger.getLogger(CSVReportCreatorTest.class.getName());

    CSVReportCreator csvReportCreator = CSVReportCreator.getInstance();

    @Test
    void whenGivenCorrectSourcePathAndReportPath_thenItCreatesNewFile() {
        //given
        String sourcePath = "/ItemTest.csv";
        String reportPath = "./src/test/resources/reportTestData.csv";
        String validationReportPath = "./src/test/resources/validationReport.csv";
        //then
        csvReportCreator.createReport(sourcePath, reportPath);
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
