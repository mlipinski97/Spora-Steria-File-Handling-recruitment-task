package pl.lipinski.creator;

import pl.lipinski.reader.DatabaseReader;
import pl.lipinski.writer.CSVWriter;

import java.util.Map;

public class DatabaseReportCreator implements SopraSteriaReportCreator {

    private static DatabaseReportCreator instance;

    public static DatabaseReportCreator getInstance() {
        if (instance == null) {
            instance = new DatabaseReportCreator();
        }
        return instance;
    }

    private DatabaseReportCreator() {
    }

    @Override
    public void createReport(String databasePath, String reportPath) {
        DatabaseReader databaseReader = DatabaseReader.getInstance();

        Map<String, Long> readCSVData = databaseReader.read(databasePath);

        CSVWriter databaseWriter = CSVWriter.getInstance();

        databaseWriter.write(reportPath, readCSVData);
    }
}
