package pl.lipinski.reader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVReaderTest {

    //object under test
    CSVReader csvReader = CSVReader.getInstance();

    @Test
    @DisplayName("when given existing file path it reads correctly and returns list key-value pairs")
    void whenGivenExistingFile_thenItReadsAndReturnsMapOfKeyValuePairs() {
        //given
        String filePath = "./src/test/resources/ItemTest.csv";
        Map<String, Long> expectedOutcome = new HashMap<>();
        expectedOutcome.put("supply", 292L);
        expectedOutcome.put("buy", 159L);
        //then
        Map<String, Long> actualOutcome = csvReader.read(filePath);
        assertEquals(expectedOutcome, actualOutcome);
    }

    @Test
    @DisplayName("when given non existing file path it creates empty file")
    void whenGivenNonExistingFile_thenItCreatesEmptyFile() {
        //given
        String nonExistingFilePath = "/itemZ.csv";
        Map<String, Long> expectedOutcome = new HashMap<>();
        //then
        Map<String, Long> actualOutcome = csvReader.read(nonExistingFilePath);
        assertEquals(expectedOutcome, actualOutcome);
    }

    @Test
    @DisplayName("when given existing file path with malformed data it throws NumberFormatException")
    void whenGivenFileWithMalformedData_thenItThrowsNumberFormatException() {
        //given
        String malformedDataFilePath = "./src/test/resources/malformedItemData.csv";
        //then
        assertThrows(NumberFormatException.class, () -> csvReader.read(malformedDataFilePath));
    }
}
