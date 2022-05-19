package pl.lipinski.creator;

import pl.lipinski.reader.CSVReader;
import pl.lipinski.writer.CSVWriter;

import java.util.Map;

public class CSVReportCreator implements SopraSteriaReportCreator {

    private static CSVReportCreator instance;

    private CSVReportCreator() {

    }

    public static CSVReportCreator getInstance() {
        if (instance == null) {
            instance = new CSVReportCreator();
        }
        return instance;
    }


    @Override
    public void createReport(String sourcePath, String reportPath) {
        //we're creating singleton reader object to read data from our csv files
        CSVReader csvReader = CSVReader.getInstance();

        //calling readCsv method which returns list of words by line (list of list of words)
        Map<String, Long> readCSVData = csvReader.read(sourcePath);

        //we create singleton writer object to write into our report
        CSVWriter csvWriter = CSVWriter.getInstance();

        //we write into new file with given name
        csvWriter.write(reportPath, readCSVData);
    }
}
