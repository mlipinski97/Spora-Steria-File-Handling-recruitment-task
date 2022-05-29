package pl.lipinski.creator;

import pl.lipinski.reader.CSVReader;
import pl.lipinski.writer.CSVWriter;

import java.util.Map;
import java.util.logging.Logger;

public class CSVReportCreator implements SopraSteriaReportCreator {

    private static final Logger logger = Logger.getLogger(CSVReportCreator.class.getName());

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
        CSVReader csvReader = CSVReader.getInstance();
        Map<String, Long> readCSVData = csvReader.read(sourcePath);
        CSVWriter csvWriter = CSVWriter.getInstance();
        csvWriter.write(reportPath, readCSVData);
        logger.info("created Database report for path: " + sourcePath + " at: " + reportPath);
    }
}
