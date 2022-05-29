package pl.lipinski;

import pl.lipinski.creator.CSVReportCreator;
import pl.lipinski.creator.DatabaseReportCreator;

public class Main {
    public static void main(String[] args) {
        CSVReportCreator.getInstance().createReport("./src/main/resources/ItemX.csv", "./src/main/resources/reportItemX.csv");
        CSVReportCreator.getInstance().createReport("./src/main/resources/ItemY.csv", "./src/main/resources/reportItemY.csv");
        DatabaseReportCreator.getInstance().createReport("jdbc:postgresql://localhost:5432/Sopra-Steria", "./src/main/resources/reportDatabase.csv");
    }
}
